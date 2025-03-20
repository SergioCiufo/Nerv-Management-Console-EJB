package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.SimulationParticipantsServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.SimulationParticipants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SimulationParticipantsService {
    @EJB
    private SimulationParticipantsServiceDao simulationParticipantsServiceDao;

    public void createParticipant(SimulationParticipants simulationParticipant) {
        simulationParticipantsServiceDao.createParticipant(simulationParticipant);
    }

    public SimulationParticipants getParticipantbyUserAndMemberId(User user, Member member) {
        return simulationParticipantsServiceDao.getParticipantbyUserAndMemberId(user, member);
    }

    public void removeParticipant(User user, Simulation simulation) {
        simulationParticipantsServiceDao.removeParticipant(user, simulation);
    }
}
