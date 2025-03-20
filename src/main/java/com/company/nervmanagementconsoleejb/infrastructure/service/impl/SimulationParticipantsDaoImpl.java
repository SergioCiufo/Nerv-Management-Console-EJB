package com.company.nervmanagementconsoleejb.infrastructure.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.SimulationParticipantsServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.SimulationParticipants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.infrastructure.atomicDao.SimulationParticipantsDaoAtomic;
import javax.ejb.EJB;
import javax.ejb.Stateless;
//da controllare l'injezione
@Stateless
public class SimulationParticipantsDaoImpl implements SimulationParticipantsServiceDao {
    @EJB
    private SimulationParticipantsDaoAtomic simulationParticipantsDaoAtomic;

    @Override
    public void createParticipant(SimulationParticipants simulationParticipants) {
        simulationParticipantsDaoAtomic.createParticipant(simulationParticipants);
    }

    @Override
    public SimulationParticipants getParticipantbyUserAndMemberId(User user, Member member) {
        return simulationParticipantsDaoAtomic.getParticipantByUserAndMemberId(user, member);
    }

    @Override
    public void removeParticipant(User user, Simulation simulation) {
        simulationParticipantsDaoAtomic.removeParticipant(user, simulation);
    }
}
