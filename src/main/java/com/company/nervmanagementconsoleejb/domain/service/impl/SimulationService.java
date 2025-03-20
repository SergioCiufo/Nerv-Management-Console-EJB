package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.SimulationServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;

@Stateless
public class SimulationService {
    @EJB
    private SimulationServiceDao simulationServiceDao;

    public List<Simulation> retrieveSimulations() {
        return simulationServiceDao.retrieveSimulations();
    }

    public List<Simulation> getSimulationAndParticipantsByUserId(User user) {
        return simulationServiceDao.getSimulationAndParticipantsByUserId(user);
    }

    public Simulation retrieveBySimulationId(int idSimulation) {
        return simulationServiceDao.retrieveBySimulationId(idSimulation);
    }

}