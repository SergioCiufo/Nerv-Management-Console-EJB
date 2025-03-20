package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MissionServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;

@Stateless
public class MissionService {
    @EJB
    private MissionServiceDao missionServiceDao;

    public List<Mission> retrieveMissions(){
        return missionServiceDao.retrieveMissions();
    }

    public Mission getMissionById(int idMission) {
        return missionServiceDao.getMissionById(idMission);
    }

    public void addMission(Mission mission) {
        missionServiceDao.addMission(mission);
    }

    public void updateMission(Mission mission) {
        missionServiceDao.updateMission(mission);
    }

    public Mission addEventMission(Mission mission) {
        return missionServiceDao.addEventMission(mission);
    }

}
