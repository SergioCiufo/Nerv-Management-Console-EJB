package com.company.nervmanagementconsoleejb.infrastructure.atomicDao;

import com.company.nervmanagementconsoleejb.domain.exception.DatabaseException;
import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;

import java.util.List;

@Stateless
public class SimulationDaoAtomic {

    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    public List<Simulation> retrieve() {
        try{
            return entityManager.createQuery("from Simulation", Simulation.class).getResultList();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error during retrieval", e);
        }
    }

    public Simulation retrieveBySimulationId(int simulationId) {
        try{
            return entityManager.createQuery("FROM Simulation s WHERE s.simulationId = :simulationId", Simulation.class)
                    .setParameter("simulationId", simulationId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error retrive simulation by simulationId: "+simulationId, e);
        }
    }

    public List<Simulation> getSimulationAndParticipantsByUserId(User user) {
        try{
            return entityManager.createQuery("From Simulation s " +
                    "join fetch s.simulationParticipants sp " +
                    "where sp.user.id = :userId", Simulation.class)
                    .setParameter("userId", user.getIdUser())
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error retrive simulation by userId: "+user.getIdUser(), e);
        }
    }
}
