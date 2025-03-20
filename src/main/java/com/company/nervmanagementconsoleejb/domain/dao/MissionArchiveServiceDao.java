
package com.company.nervmanagementconsoleejb.domain.dao;

import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.MissionArchive;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Local;

import java.util.List;

@Local
public interface MissionArchiveServiceDao {
    public List<MissionArchive> retriveByUserIdAndIdMission(User user, Mission mission);
    public void addMissionArchive(MissionArchive missionArchive);
    public MissionArchive retriveByUserIdAndIdMissionResultProgress(User user, Mission mission);
    public void updateMissionResult(MissionArchive missionArchive, MissionArchive.MissionResult missionResult, int missionId);
    public List<MissionArchive> retriveByUserId(int userId);
}
