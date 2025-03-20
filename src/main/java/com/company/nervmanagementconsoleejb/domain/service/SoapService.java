package com.company.nervmanagementconsoleejb.domain.service;

import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SoapService {
    List<User> getUsersList() throws Exception;
    void register(User user) throws Exception;
    void updateUser(User user) throws Exception;
    void removeUser(int userId) throws Exception;
    Mission addEventMission(Mission mission) throws Exception;

}
