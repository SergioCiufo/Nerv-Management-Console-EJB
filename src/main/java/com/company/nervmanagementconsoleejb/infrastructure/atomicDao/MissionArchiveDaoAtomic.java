
package com.company.nervmanagementconsoleejb.infrastructure.atomicDao;

import com.company.nervmanagementconsoleejb.domain.exception.DatabaseException;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.MissionArchive;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;

import java.util.List;

@Stateless
public class MissionArchiveDaoAtomic {

    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    public void addMissionArchive(MissionArchive missionArchive) {
        try {
            entityManager.persist(missionArchive);
        } catch (HibernateException e) {
            throw new DatabaseException("Unexpected error create MissionArchive" + missionArchive.getMissionArchiveId(), e);
        }
    }

    public List<MissionArchive> retriveByUserIdAndIdMission(User user, Mission mission) {
        try {
            return entityManager.createQuery("FROM MissionArchive ma "
                            + "WHERE ma.user.id = :userId AND ma.mission.missionId = :missionId", MissionArchive.class)
                    .setParameter("userId", user.getIdUser())
                    .setParameter("missionId", mission.getMissionId())
                    .getResultList();
        } catch (HibernateException e) {
            throw new DatabaseException("Error retrive MissionArchive, userId: " + user.getIdUser() + " and missionId: " + mission.getMissionId(), e);
        }
    }

    public List<MissionArchive> retriveByUserId(int userId) {
        try {
            return entityManager.createQuery("FROM MissionArchive ma "
                            + "WHERE ma.user.id = :userId", MissionArchive.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (HibernateException e) {
            throw new DatabaseException("Error retrive MissionArchive, userId: " + userId, e);
        }
    }

    public MissionArchive retriveByUserIdAndIdMissionResultProgress(User user, Mission mission) {
        try {
            return entityManager.createQuery("FROM MissionArchive "
                            + "WHERE user.idUser = :userId AND mission.missionId = :missionId AND result = 'PROGRESS'", MissionArchive.class)
                    .setParameter("userId", user.getIdUser())
                    .setParameter("missionId", mission.getMissionId())
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
        } catch (HibernateException e) {
            throw new DatabaseException("Error retrieving MissionArchive in Progress for userId: " + user.getIdUser() + " and missionId: " + mission.getMissionId() + " ", e);
        }
    }

    public void updateMissionResult(MissionArchive missionArchive, MissionArchive.MissionResult result, int missionId) {
        try {
            entityManager.createQuery("UPDATE MissionArchive ma SET ma.result = :result "
                            + "WHERE ma.result = :progress AND ma.mission.missionId = :missionId")
                    .setParameter("result", result)
                    .setParameter("progress", MissionArchive.MissionResult.PROGRESS)
                    .setParameter("missionId", missionId)
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new DatabaseException("Error updating MissionArchive: " + missionArchive.getMissionArchiveId(), e);
        }
    }
}
