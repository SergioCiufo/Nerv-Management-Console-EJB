
package com.company.nervmanagementconsoleejb.infrastructure.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MissionArchiveServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.MissionArchive;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.infrastructure.atomicDao.MissionArchiveDaoAtomic;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;
//da controllare l'injezione
@Stateless
public class MissionArchiveServiceDaoImpl implements MissionArchiveServiceDao {
    @EJB
    private MissionArchiveDaoAtomic missionArchiveDaoAtomic;

    @Override
    public List<MissionArchive> retriveByUserIdAndIdMission(User user, Mission mission) {
        return missionArchiveDaoAtomic.retriveByUserIdAndIdMission(user, mission);
    }

    @Override
    public void addMissionArchive(MissionArchive missionArchive) {
        missionArchiveDaoAtomic.addMissionArchive(missionArchive);
    }

    @Override
    public MissionArchive retriveByUserIdAndIdMissionResultProgress(User user, Mission mission) {
        return missionArchiveDaoAtomic.retriveByUserIdAndIdMissionResultProgress(user, mission);
    }

    @Override
    public void updateMissionResult(MissionArchive missionArchive, MissionArchive.MissionResult missionResult, int missionId) {
        missionArchiveDaoAtomic.updateMissionResult(missionArchive, missionResult, missionId);
    }

    @Override
    public List<MissionArchive> retriveByUserId(int userId) {
        return missionArchiveDaoAtomic.retriveByUserId(userId);
    }
}
