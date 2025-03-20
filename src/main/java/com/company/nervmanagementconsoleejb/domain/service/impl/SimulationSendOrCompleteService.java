package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.model.*;
import com.company.nervmanagementconsoleejb.domain.utils.CalculateUtils;
import com.company.nervmanagementconsoleejb.domain.utils.LevelUpUtils;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Stateless
public class SimulationSendOrCompleteService {
    @EJB
    private SimulationService simulationService;
    @EJB
    private MemberService memberService;
    @EJB
    private UserMemberStatsService userMemberStatsService;
    @EJB
    private SimulationParticipantsService simulationParticipantsService;
    @EJB
    private RetriveInformationService retriveInformationService;

    public User sendMemberSimulation (User user, String idStringMember, String idStringSimulation) throws SQLException {
        int idMember = Integer.parseInt(idStringMember);
        Member member = memberService.retrieveByMemberId(idMember);
        int idSimulation = Integer.parseInt(idStringSimulation);
        Simulation simulation = simulationService.retrieveBySimulationId(idSimulation);
        LocalDateTime startTime = LocalDateTime.now();

        int duration = simulation.getDurationTime();

        LocalDateTime endTime = startTime.plusMinutes(duration);

        userMemberStatsService.updateMembStatsStartSim(user, member);
//        SimulationParticipants simParticipant = new SimulationParticipants(simulation, user, member, startTime, endTime);
        SimulationParticipants simParticipant = SimulationParticipants.builder()
                .simulation(simulation)
                .user(user)
                .member(member)
                .startTime(startTime)
                .endTime(endTime)
                .build();
        simulationParticipantsService.createParticipant(simParticipant);
        user=retriveInformationService.retriveUserInformation(user);
        return user;
    }

    public User completeSimulation (User user, String idStringMember, String idStringSimulation) throws SQLException {
        SimulationParticipants simPart;
        int idMember = Integer.parseInt(idStringMember);
        Member member = memberService.retrieveByMemberId(idMember);
        int idSimulation = Integer.parseInt(idStringSimulation);
        Simulation simulation = simulationService.retrieveBySimulationId(idSimulation);

        simPart= simulationParticipantsService.getParticipantbyUserAndMemberId(user, member);
        if(simPart.getEndTime().isBefore(LocalDateTime.now())) {

            UserMembersStats ums;
            ums= userMemberStatsService.retrieveStatsByUserAndMember(user, member);

            CalculateUtils calculateUtils = new CalculateUtils();

            Integer suppAbility = simulation.getSupportAbility();
            suppAbility = calculateUtils.randomizeStats(suppAbility);
            suppAbility =(suppAbility+ums.getSupportAbility());
            suppAbility = calculateUtils.MinMaxStat(suppAbility);
            ums.setSupportAbility(suppAbility);

            Integer sincRate = simulation.getSynchronizationRate();
            sincRate = calculateUtils.randomizeStats(sincRate);
            sincRate = (sincRate+ums.getSynchronizationRate());
            sincRate = calculateUtils.MinMaxStat(sincRate);
            ums.setSynchronizationRate(sincRate);

            Integer tactAbility = simulation.getTacticalAbility();
            tactAbility= calculateUtils.randomizeStats(tactAbility);
            tactAbility = (tactAbility+ums.getTacticalAbility());
            tactAbility = calculateUtils.MinMaxStat(tactAbility);
            ums.setTacticalAbility(tactAbility);

            Integer newExp =simulation.getExp();
            newExp = calculateUtils.randomizeStats(newExp);

            LevelUpUtils levelUpUtils = new LevelUpUtils();
            ums=levelUpUtils.levelUp(ums, newExp);

            userMemberStatsService.updateMembStatsCompletedSim(user, member, ums);
            simulationParticipantsService.removeParticipant(user, simulation);
        }
        user=retriveInformationService.retriveUserInformation(user);
        return user;
    }

}
