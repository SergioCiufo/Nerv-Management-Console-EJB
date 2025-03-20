package com.company.nervmanagementconsoleejb.domain.dao;

import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.MissionParticipants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Local;

import java.util.List;

@Local
public interface MissionParticipantsServiceDao {
    public List<MissionParticipants> getMissionParticipantsByUserIdAndMissionId(User user, Mission mission);
    public void startMission(MissionParticipants missionParticipants);
    public void removeParticipant(User user, Mission mission);
    public List<MissionParticipants> getActiveMissionsByUserId(int userId);
}
