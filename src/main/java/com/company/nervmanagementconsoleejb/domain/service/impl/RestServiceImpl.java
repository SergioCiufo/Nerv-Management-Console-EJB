package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.model.*;
import com.company.nervmanagementconsoleejb.domain.service.RestService;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RestServiceImpl implements RestService {
    @EJB
    private UserService userService;

    @EJB
    private RegisterService registerService;

    @EJB
    private UserMemberStatsService userMemberStatsService;

    @EJB
    private MissionService missionService;

    @EJB
    private MissionParticipantsService missionParticipantsService;

    @EJB
    private MissionArchiveService missionArchiveService;


    @Override
    public User getUserById(int userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @Override
    public void register(User user) {
        registerService.register(user);
    }

    @Override
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @Override
    public void removeUser(int userId) {
        userService.removeUser(userId);
    }

    @Override
    public List<User> getUsersbyNameAndOrSurname(String name, String surname) {
        return userService.getUsersbyNameAndOrSurname(name, surname);
    }

    @Override
    public List<UserMembersStats> retrieveByUserId(int userId) {
        return userMemberStatsService.retrieveByUserId(userId);
    }

    @Override
    public List<Mission> retrieveMissions() {
        return missionService.retrieveMissions();
    }

    @Override
    public void addMission(Mission mission) {
        missionService.addMission(mission);
    }

    @Override
    public void updateMission(Mission mission) {
        missionService.updateMission(mission);
    }

    @Override
    public List<MissionParticipants> getActiveMissionsByUserId(int userId) {
        return missionParticipantsService.getActiveMissionsByUserId(userId);
    }

    @Override
    public List<MissionArchive> retriveByUserId(int userId) {
        return missionArchiveService.retriveByUserId(userId);
    }
}
