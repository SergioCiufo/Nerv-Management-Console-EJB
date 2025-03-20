package com.company.nervmanagementconsoleejb.infrastructure.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MissionServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.infrastructure.atomicDao.MissionDaoAtomic;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import java.util.List;
//da controllare l'injezione
@Stateless
public class MissionServiceDaoImpl implements MissionServiceDao {
    @EJB
    private MissionDaoAtomic missionDaoAtomic;

    @Override
    public List<Mission> retrieveMissions() {
        return missionDaoAtomic.retrieve();
    }

    @Override
    public Mission getMissionById(int idMission) {
        return missionDaoAtomic.getMissionById(idMission);
    }

    @Override
    public void addMission(Mission mission) {
        missionDaoAtomic.create(mission);
    }

    @Override
    public void updateMission(Mission mission) {
        missionDaoAtomic.updateMission(mission);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Mission addEventMission(Mission mission) {
        missionDaoAtomic.updateEventMissionByAvailableTrue();
        missionDaoAtomic.create(mission);
        return mission;
    }
}
