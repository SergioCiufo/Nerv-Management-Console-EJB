package com.company.nervmanagementconsoleejb.domain.service;

import com.company.nervmanagementconsoleejb.domain.model.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RestService {
    User getUserById(int userId);
    User getUserByUsername(String username);
    void register(User user);
    void updateUser(User user);
    void removeUser(int userId);
    List<User> getUsersbyNameAndOrSurname(String name, String surname);

    List<UserMembersStats> retrieveByUserId(int userId);

    List<Mission> retrieveMissions();
    void addMission(Mission mission);
    void updateMission(Mission mission);
    List<MissionParticipants> getActiveMissionsByUserId(int userId);

    List<MissionArchive> retriveByUserId(int userId);
}
