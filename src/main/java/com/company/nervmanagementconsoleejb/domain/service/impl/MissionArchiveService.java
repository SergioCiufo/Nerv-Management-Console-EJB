
package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MissionArchiveServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.MissionArchive;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;


import java.util.List;


@Stateless
public class MissionArchiveService {
    @EJB
    private MissionArchiveServiceDao missionArchiveServiceDao;

    public List<MissionArchive> retriveByUserIdAndIdMission(User user, Mission mission) {
        return missionArchiveServiceDao.retriveByUserIdAndIdMission(user, mission);
    }

    public void addMissionArchive(MissionArchive missionArchive) {
        missionArchiveServiceDao.addMissionArchive(missionArchive);
    }

    public MissionArchive retriveByUserIdAndIdMissionResultProgress (User user, Mission mission) {
        return missionArchiveServiceDao.retriveByUserIdAndIdMissionResultProgress(user, mission);
    }

    public void updateMissionResult (MissionArchive missionArchive, MissionArchive.MissionResult missionResult, int missionId) {
        missionArchiveServiceDao.updateMissionResult(missionArchive, missionResult, missionId);
    }

    public List<MissionArchive> retriveByUserId (int userId) {
        return missionArchiveServiceDao.retriveByUserId(userId);
    }
}

