package com.company.nervmanagementconsoleejb.domain.dao;

import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Local;

import java.util.List;

@Local
public interface SimulationServiceDao {
    public List<Simulation> retrieveSimulations();
    public List<Simulation> getSimulationAndParticipantsByUserId(User user);
    public Simulation retrieveBySimulationId(int simulationId);
}
