package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.model.*;
import com.company.nervmanagementconsoleejb.domain.utils.LevelUpUtils;
//import com.company.nervmanagementconsoleejb.domain.utils.MissionCodeGeneratorUtils;
//import com.company.nervmanagementconsoleejb.domain.utils.MissionCodeGeneratorUtils;
import com.company.nervmanagementconsoleejb.domain.utils.MissionResultUtils;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MissionSendOrCompleteService {
    @EJB
    private MissionService missionService;
    @EJB
    private UserMemberStatsService userMemberStatsService;
    @EJB
    private MissionParticipantsService missionParticipantsService;
    @EJB
    private MissionArchiveService missionArchiveService;
    @EJB
    private RetriveInformationService retriveInformationService;

    public User sendMembersMission(User user, String idMissionString, List<String> idMembersString) throws SQLException {
        List<Integer> idMembers = new ArrayList<>();
        if (idMembersString != null) {
            int idMembPars = 0;
            for (String idMembString : idMembersString) {
                idMembPars = Integer.parseInt(idMembString);
                idMembers.add(idMembPars);
            }
        }

        int idMission = Integer.parseInt(idMissionString);
        LocalDateTime startTime = LocalDateTime.now();
        Mission mission = missionService.getMissionById(idMission);
        int duration = mission.getDurationTime();
        LocalDateTime endTime = startTime.plusMinutes(duration);
        Integer tactAbility;
        Integer synchRate;
        Integer suppAbility;

        for (Member memb : user.getMembers()) {
            memb.setUserMembersStats(userMemberStatsService.retrieveStatsByUserAndMember(user, memb));
            if (idMembers.contains(memb.getIdMember())) {
                UserMembersStats stats = memb.getUserMembersStats();
                if (stats != null) {
                    tactAbility = stats.getTacticalAbility();
                    synchRate = stats.getSynchronizationRate();
                    suppAbility = stats.getSupportAbility();

                    MissionArchive missionArchive = MissionArchive.builder()
                            .mission(mission)
                            .user(user)
                            .member(memb)
                            .startTime(startTime)
                            .endTime(endTime)
                            .supportAbility(suppAbility)
                            .synchRate(synchRate)
                            .tacticalAbility(tactAbility)
                            .result(MissionArchive.MissionResult.PROGRESS)
                            .build();
                    missionArchiveService.addMissionArchive(missionArchive);
                    MissionParticipants missPartecipants = MissionParticipants.builder()
                            .mission(mission)
                            .user(user)
                            .member(memb)
                            .build();
                    missionParticipantsService.startMission(missPartecipants);
                    userMemberStatsService.updateMembStatsStartSim(user, memb);
                }
            }
        }

        User returnUser = new User();
        returnUser.setIdUser(user.getIdUser());
        returnUser = retriveInformationService.retriveUserInformation(returnUser);
        return returnUser;

    }

    public User completeMission(User user, String idMissionString) throws SQLException {
        int idMission = Integer.parseInt(idMissionString);
        Mission mission = missionService.getMissionById(idMission);
        MissionArchive missionArchive = missionArchiveService.retriveByUserIdAndIdMissionResultProgress(user, mission);
        List<MissionParticipants> missionParticipants = missionParticipantsService.getMissionParticipantsByUserIdAndMissionId(user, mission);
        mission.setMissionParticipants(missionParticipants);
        //for ums che fa add con metodo ritira by user e member id
        List<UserMembersStats> ums = new ArrayList<UserMembersStats>();
        for (MissionParticipants mp : missionParticipants) {
            UserMembersStats umStats = userMemberStatsService.retrieveStatsByUserAndMember(user, mp.getMember());
            ums.add(umStats);
        }

        //Boolean result = null;
        MissionResultUtils missionResultUtils = new MissionResultUtils();
        Boolean result = missionResultUtils.missionResult(mission, idMission, ums);
        Integer newExp = mission.getExp();

        LevelUpUtils levelUpUtils = new LevelUpUtils();
        for (UserMembersStats uMemberStats : ums) {
            if (result == true) {
                uMemberStats = levelUpUtils.levelUp(uMemberStats, newExp);
                uMemberStats.setStatus(true);
                userMemberStatsService.updateMembStatsCompletedMission(uMemberStats);
            } else {
                uMemberStats.setStatus(true);
                userMemberStatsService.updateMembStatsCompletedMission(uMemberStats);
            }
        }

        if (result) {
            missionArchiveService.updateMissionResult(missionArchive, MissionArchive.MissionResult.WIN, idMission);
        } else {
            missionArchiveService.updateMissionResult(missionArchive, MissionArchive.MissionResult.LOSE, idMission);
        }
        missionParticipantsService.removeParticipant(user, mission);

        user = retriveInformationService.retriveUserInformation(user);
        return user;
    }
}