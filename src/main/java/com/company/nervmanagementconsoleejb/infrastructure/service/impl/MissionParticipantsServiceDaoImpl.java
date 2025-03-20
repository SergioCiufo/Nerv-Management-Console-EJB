package com.company.nervmanagementconsoleejb.infrastructure.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MissionParticipantsServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.MissionParticipants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.infrastructure.atomicDao.MissionParticipantsDaoAtomic;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;
//da controllare l'injezione
@Stateless
public class MissionParticipantsServiceDaoImpl implements MissionParticipantsServiceDao {
    @EJB
    MissionParticipantsDaoAtomic missionParticipantsDaoAtomic;

    @Override
    public List<MissionParticipants> getMissionParticipantsByUserIdAndMissionId(User user, Mission mission) {
        return missionParticipantsDaoAtomic.getMissionParticipantsByUserIdAndMissionId(user, mission);
    }

    @Override
    public void startMission(MissionParticipants missionParticipants) {
        missionParticipantsDaoAtomic.startMission(missionParticipants);
    }

    @Override
    public void removeParticipant(User user, Mission mission) {
        missionParticipantsDaoAtomic.removeParticipant(user, mission);
    }

    @Override
    public List<MissionParticipants> getActiveMissionsByUserId(int userId) {
        return missionParticipantsDaoAtomic.getActiveMissionsByUserId(userId);
    }
}
