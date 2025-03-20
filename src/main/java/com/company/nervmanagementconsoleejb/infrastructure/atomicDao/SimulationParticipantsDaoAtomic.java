package com.company.nervmanagementconsoleejb.infrastructure.atomicDao;

import com.company.nervmanagementconsoleejb.domain.exception.DatabaseException;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.SimulationParticipants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;

@Stateless
public class SimulationParticipantsDaoAtomic {

    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    public void createParticipant(SimulationParticipants simulationParticipants) {
        try{
            entityManager.persist(simulationParticipants);
        }catch(HibernateException e){
            throw new DatabaseException("Unexpected error create simulationPartecipant id: " + simulationParticipants.getSimulationParticipantId(), e);
        }
    }

    public SimulationParticipants getParticipantByUserAndMemberId(User user, Member member){
        try{
            return entityManager.createQuery("FROM SimulationParticipants sp "
                            + "WHERE sp.user.id = :userId AND sp.member.id = :memberId", SimulationParticipants.class)
                    .setParameter("userId", user.getIdUser())
                    .setParameter("memberId", member.getIdMember())
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error retrive simulationPartecipant by userid: " + user.getIdUser(), e);
        }
    }

    public void removeParticipant(User user, Simulation simulation) {
        try{
            entityManager.createQuery("DELETE FROM SimulationParticipants sp " +
                            "WHERE sp.user.id = :userId AND sp.simulation.id = :simulationId")
                    .setParameter("userId", user.getIdUser())
                    .setParameter("simulationId", simulation.getSimulationId())
                    .executeUpdate();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error removing participant userid: " + user.getIdUser() + "simId: "+ simulation.getSimulationId() , e);
        }
    }
}
