package com.company.nervmanagementconsoleejb.domain.dao;

import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.SimulationParticipants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Local;

@Local
public interface SimulationParticipantsServiceDao {
    public void createParticipant(SimulationParticipants simulationParticipants);
    public SimulationParticipants getParticipantbyUserAndMemberId(User user, Member member);
    public void removeParticipant(User user, Simulation simulation);
}
