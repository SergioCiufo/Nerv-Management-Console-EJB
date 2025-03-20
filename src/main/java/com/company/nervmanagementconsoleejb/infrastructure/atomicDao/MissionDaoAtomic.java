package com.company.nervmanagementconsoleejb.infrastructure.atomicDao;

import com.company.nervmanagementconsoleejb.domain.exception.DatabaseException;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;

import java.util.List;

@Stateless
public class MissionDaoAtomic {

    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    public void create(Mission mission) {
        try{
            entityManager.persist(mission);
        }catch(HibernateException e){
            throw new DatabaseException("Unexpected error create mission" + mission.getMissionId(), e);
        }
    }

    public List<Mission> retrieve(){
        try{
            return entityManager.createQuery("from Mission m order by m.missionId ASC", Mission.class).getResultList();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error retrieve missions", e);
        }
    }

    public Mission getMissionById(int missionId){
        try{
            return entityManager.createQuery("FROM Mission m WHERE m.missionId = :missionId", Mission.class)
                    .setParameter("missionId", missionId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (HibernateException e){
            throw new DatabaseException("Error while getting mission, missionId: "+missionId, e);
        }
    }

    public void updateMission(Mission mission){
        try{
            entityManager.merge(mission);
        }catch (HibernateException e){
            throw new DatabaseException("Error while updating mission, missionId" + mission.getMissionId(), e);
        }
    }

    public void updateEventMissionByAvailableTrue(){
        try{
            entityManager.createQuery("update Mission m " +
                    "SET m.available = false " +
                    "WHERE m.eventMission = true")
                    .executeUpdate();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error during updating MissionEvent", e);
        }
    }
}
