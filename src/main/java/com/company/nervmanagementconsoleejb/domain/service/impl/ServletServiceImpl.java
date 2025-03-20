package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.service.ServletService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class ServletServiceImpl implements ServletService {

    @EJB
    private LoginService loginService;

    @EJB
    private RegisterService registerService;

    @EJB
    private SimulationSendOrCompleteService simulationSendOrCompleteService;

    @EJB
    private MissionSendOrCompleteService missionSendOrCompleteService;

//    @Override
//    public LoginResponse login(LoginRequest loginRequest) throws Exception {
//        String username = loginRequest.getUsername();
//        String password = loginRequest.getPassword();
//        User user = loginService.loginCheck(username, password);
//        return LoginResponse.builder()
//                .user(user)
//                .build();
//    }

    @Override
    public User login(User user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        user = loginService.loginCheck(username, password);
        return user;
    }

//    @Override
//    public RegisterResponse register(RegisterRequest registerRequest) {
//        User newUser = User.builder()
//                .name(registerRequest.getName())
//                .surname(registerRequest.getSurname())
//                .username(registerRequest.getUsername())
//                .password(registerRequest.getPassword())
//                .build();
//        registerService.register(newUser);
//        return RegisterResponse.builder()
//                .message("Successfully registered")
//                .build();
//    }

    @Override
    public void register(User user) {
        registerService.register(user);
    }

//    @Override
//    public FirstStepSimulationResponse simulation(FirstStepSimulationRequest firstStepSimulationRequest) throws SQLException {
//        User user = simulationSendOrCompleteService.sendMemberSimulation(firstStepSimulationRequest.getUser(), firstStepSimulationRequest.getIdMember(), firstStepSimulationRequest.getIdSimulation());
//        return FirstStepSimulationResponse.builder()
//                .user(user)
//                .build();
//    }
//
//    @Override
//    public SecondStepSimulationCompletedResponse completeSimulation(SecondStepSimulationCompletedRequest secondStepSimulationCompletedRequest) throws SQLException {
//        User user = simulationSendOrCompleteService.completeSimulation(secondStepSimulationCompletedRequest.getUser(), secondStepSimulationCompletedRequest.getIdMember(), secondStepSimulationCompletedRequest.getIdSimulation());
//        return SecondStepSimulationCompletedResponse.builder()
//                .user(user)
//                .build();
//    }
//
//    @Override
//    public FirstStepMissionResponse mission(FirstStepMissionRequest firstStepMissionRequest) throws SQLException {
//        ArrayList<String> selectedIdMembersListString = new ArrayList<>(Arrays.asList(firstStepMissionRequest.getSelectedIdMembers()));
//        User user = missionSendOrCompleteService.sendMembersMission(firstStepMissionRequest.getUser(), firstStepMissionRequest.getMissionId(), selectedIdMembersListString);
//        return FirstStepMissionResponse.builder()
//                .user(user)
//                .build();
//    }
//
//    @Override
//    public SecondStepMissionCompletedResponse completeMission(SecondStepMissionCompletedRequest secondStepMissionCompletedRequest) throws SQLException {
//        User user = missionSendOrCompleteService.completeMission(secondStepMissionCompletedRequest.getUser(), secondStepMissionCompletedRequest.getMissionId());
//        return SecondStepMissionCompletedResponse.builder()
//                .user(user)
//                .build();
//    }

    @Override
    public User simulation(User user, String idSimulation, String idMember) throws Exception {
        return simulationSendOrCompleteService.sendMemberSimulation(user, idSimulation, idMember);
    }

    @Override
    public User completeSimulation(User user, String idSimulation, String idMember) throws Exception {
        return simulationSendOrCompleteService.completeSimulation(user, idSimulation, idMember);
    }

    @Override
    public User mission(User user, String idMissionString, String selectedIdMembers) throws SQLException {
        ArrayList<String> selectedIdMembersListString = new ArrayList<>(Arrays.asList(selectedIdMembers));
        return missionSendOrCompleteService.sendMembersMission(user, idMissionString, selectedIdMembersListString);
    }

    @Override
    public User completeMission(User user, String idMissionString) throws Exception {
        return missionSendOrCompleteService.completeMission(user, idMissionString);
    }
}
