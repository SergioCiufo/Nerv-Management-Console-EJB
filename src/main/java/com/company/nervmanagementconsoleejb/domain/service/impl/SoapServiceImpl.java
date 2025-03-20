package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.service.SoapService;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SoapServiceImpl implements SoapService {
    @EJB
    private RegisterService registerService;

    @EJB
    private UserService userService;

    @EJB
    private MissionService missionService;

    @Override
    public List<User> getUsersList() throws Exception {
        return userService.getUsersList();
    }

    @Override
    public void register(User user) throws Exception {
        registerService.register(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        userService.updateUser(user);
    }

    @Override
    public void removeUser(int userId) throws Exception {
        userService.removeUser(userId);
    }

    @Override
    public Mission addEventMission(Mission mission) throws Exception {
        return missionService.addEventMission(mission);
    }
}
