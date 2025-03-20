package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MissionParticipantsServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.MissionParticipants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;


import java.util.List;

@Stateless
public class MissionParticipantsService {
    @EJB
    private MissionParticipantsServiceDao missionParticipantsServiceDao;

    public List<MissionParticipants> getMissionParticipantsByUserIdAndMissionId (User user, Mission mission) {
        return missionParticipantsServiceDao.getMissionParticipantsByUserIdAndMissionId(user, mission);
    }

    public void startMission(MissionParticipants missionParticipants) {
        missionParticipantsServiceDao.startMission(missionParticipants);
    }

    public void removeParticipant(User user, Mission mission) {
        missionParticipantsServiceDao.removeParticipant(user, mission);
    }

    public List<MissionParticipants> getActiveMissionsByUserId (int userId) {
        return missionParticipantsServiceDao.getActiveMissionsByUserId(userId);
    }

}
