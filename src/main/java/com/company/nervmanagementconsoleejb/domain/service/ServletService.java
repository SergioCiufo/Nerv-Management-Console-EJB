package com.company.nervmanagementconsoleejb.domain.service;

import com.company.nervmanagementconsoleejb.domain.model.User;

import javax.ejb.Local;
import java.sql.SQLException;

@Local
public interface ServletService {
//    LoginResponse login(LoginRequest loginRequest) throws Exception;
    User login(User user) throws Exception;
//    RegisterResponse register(RegisterRequest registerRequest);
    void register(User user);
//    FirstStepSimulationResponse simulation(FirstStepSimulationRequest firstStepSimulationRequest) throws SQLException;
    User simulation(User user, String idSimulation, String idMember) throws Exception;
//    SecondStepSimulationCompletedResponse completeSimulation(SecondStepSimulationCompletedRequest secondStepSimulationCompletedRequest) throws SQLException;
    User completeSimulation(User user, String idSimulation, String idMember) throws Exception;
//    FirstStepMissionResponse mission(FirstStepMissionRequest firstStepMissionRequest) throws SQLException;
    User mission(User user, String idMissionString, String selectedIdMembers) throws SQLException;
//    SecondStepMissionCompletedResponse completeMission(SecondStepMissionCompletedRequest secondStepMissionCompletedRequest) throws SQLException;
    User completeMission(User user, String idMissionString) throws Exception;
}
