package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.model.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class RetriveInformationService {
    @EJB
    private MemberService memberService;
    @EJB
    private UserMemberStatsService userMemberStatsService;
    @EJB
    private SimulationService simulationService;
    @EJB
    private MissionService missionService;
    @EJB
    private MissionParticipantsService missionParticipantsService;
    @EJB
    private MissionArchiveService missionArchiveService;
    @EJB
    private UserService userService;


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User retriveUserInformation(User user) throws SQLException {
        user = userService.getUserById(user.getIdUser());
        List<Member> memberList = memberService.retrieveMembers();
        List<Simulation> simulationList = simulationService.retrieveSimulations();

        user.setMembers(memberList);
        user.setSimulations(simulationList);
        if (memberList != null) {
            for (Member member : memberList) {

                UserMembersStats stats = userMemberStatsService.retrieveStatsByUserAndMember(user, member);
                member.setUserMembersStats(stats);
            }

            //primo retrive
            if (user.getParticipants() == null) {
                user.setParticipants(new ArrayList<>());
            }
            List<Simulation> simulations = simulationService.getSimulationAndParticipantsByUserId(user);

            if (simulations != null && !simulations.isEmpty()) {
                user.getParticipants().clear();
                for (Simulation simulation : simulations) {
                    if (simulation.getSimulationParticipants() != null) {
                        for (SimulationParticipants participant : simulation.getSimulationParticipants()) {
                            user.getParticipants().add(participant);
                        }
                    }
                }
            } else {
                user.getParticipants().clear();
            }
//
//            //secondo retrive
            List<Mission> mission = missionService.retrieveMissions();
            List<MissionArchive> missionArchive = new ArrayList<>();
            List<MissionParticipants> allMissionParticipants = new ArrayList<>();
            for (Mission m : mission) {
                List<MissionParticipants> missionParticipants = missionParticipantsService.getMissionParticipantsByUserIdAndMissionId(user, m);
                allMissionParticipants.addAll(missionParticipants);
                m.setMissionParticipants(missionParticipants);
                List<MissionArchive> archives = missionArchiveService.retriveByUserIdAndIdMission(user, m);
                missionArchive.addAll(archives);
            }
            user.setMissionParticipants(allMissionParticipants);
            user.setMissions(mission);
            user.setMissionArchive(missionArchive);

//            Map<String, List<MissionArchive>> missionArchiveMap = new LinkedHashMap<>();
//            for (MissionArchive mArc : missionArchive) {
//                String missionCode = mArc.getMissionCode();
//                String[] parts = missionCode.split("-");
//                String missionKey = parts[0];
//                missionArchiveMap.putIfAbsent(missionKey, new ArrayList<>());
//                missionArchiveMap.get(missionKey).add(mArc);
//            }

//            // Ordinamento delle chiavi e delle liste
//            Map<String, List<MissionArchive>> orderKeyMap = new LinkedHashMap<>();
//            missionArchiveMap.entrySet().stream()
//                    .sorted(Map.Entry.comparingByKey())
//                    .forEachOrdered(entry -> {
//                        List<MissionArchive> archives = entry.getValue();
//
//                        // Ordinamento della lista di MissionArchive per numero del tentativo
//                        archives.sort((m1, m2) -> {
//                            String code1 = m1.getMissionCode();
//                            String code2 = m2.getMissionCode();
//
//                            int attempt1 = Integer.parseInt(code1.substring(code1.lastIndexOf("-") + 1));
//                            int attempt2 = Integer.parseInt(code2.substring(code2.lastIndexOf("-") + 1));
//                            return Integer.compare(attempt1, attempt2);
//                        });
//                        orderKeyMap.put(entry.getKey(), archives);
//                    });
//            user.setMissionArchiveResult(orderKeyMap);
//
//            System.out.println(user.getMissionArchiveResult());

            //terzo retrive
            UserMembersStats ums = null;
            for (Member m : user.getMembers()) {
                ums = userMemberStatsService.retrieveStatsByUserAndMember(user, m);
                m.setUserMembersStats(ums);
            }
        }

        return user;
    }
}