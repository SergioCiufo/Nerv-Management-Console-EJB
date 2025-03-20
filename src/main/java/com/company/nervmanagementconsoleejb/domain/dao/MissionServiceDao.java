package com.company.nervmanagementconsoleejb.domain.dao;

import com.company.nervmanagementconsoleejb.domain.model.Mission;
import javax.ejb.Local;

import java.util.List;

@Local
public interface MissionServiceDao {
    public List<Mission> retrieveMissions();
    public Mission getMissionById(int idMission);
    public void addMission(Mission mission);
    public void updateMission(Mission mission);
    public Mission addEventMission(Mission mission);
}
