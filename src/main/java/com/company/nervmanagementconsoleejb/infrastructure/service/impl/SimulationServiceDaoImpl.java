package com.company.nervmanagementconsoleejb.infrastructure.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.SimulationServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.infrastructure.atomicDao.SimulationDaoAtomic;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;
//da controllare l'injezione
@Stateless
public class SimulationServiceDaoImpl implements SimulationServiceDao {
    @EJB
    SimulationDaoAtomic simulationDaoAtomic;

    @Override
    public List<Simulation> retrieveSimulations() {
        return simulationDaoAtomic.retrieve();
    }

    @Override
    public List<Simulation> getSimulationAndParticipantsByUserId(User user) {
        return simulationDaoAtomic.getSimulationAndParticipantsByUserId(user);
    }

    @Override
    public Simulation retrieveBySimulationId(int simulationId) {
        return simulationDaoAtomic.retrieveBySimulationId(simulationId);
    }
}
