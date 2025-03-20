package com.company.nervmanagementconsoleejb.infrastructure.atomicDao;

import com.company.nervmanagementconsoleejb.domain.exception.DatabaseException;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.model.MissionParticipants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;

import java.util.List;

@Stateless
public class MissionParticipantsDaoAtomic {

    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    public void startMission(MissionParticipants missionParticipants) {
        try {
            entityManager.persist(missionParticipants);
        } catch (HibernateException e) {
            throw new DatabaseException("Unexpected error create mission, idMission:" + missionParticipants.getMissionParticipantsId(), e);
        }
    }

    public List<MissionParticipants> getMissionParticipantsByUserIdAndMissionId(User user, Mission mission) {
        try {
            return entityManager.createQuery("FROM MissionParticipants mp "
                            + "WHERE mp.user.id = :userId AND mp.mission.missionId = :missionId", MissionParticipants.class)
                    .setParameter("userId", user.getIdUser())
                    .setParameter("missionId", mission.getMissionId())
                    .getResultList();
        } catch (HibernateException e) {
            throw new DatabaseException("Error retrive missionParticipant by userId: " + user.getIdUser() + "and missionId: " + mission.getMissionId(), e);
        }
    }

    public List<MissionParticipants> getActiveMissionsByUserId(int userId) {
        try {
            return entityManager.createQuery("FROM MissionParticipants mp "
                            + "WHERE mp.user.id = :userId", MissionParticipants.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (HibernateException e) {
            throw new DatabaseException("Error retrive missionParticipant by userId: " + userId, e);
        }
    }

    public void removeParticipant(User user, Mission mission) {
        try {
            entityManager.createQuery("DELETE FROM MissionParticipants mp " +
                            "WHERE mp.user.id = :userId AND mp.mission.id = :missionId")
                    .setParameter("userId", user.getIdUser())
                    .setParameter("missionId", mission.getMissionId())
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new DatabaseException("Error removing missionParticipant by userId: " + user.getIdUser() + " and missionId: " + mission.getMissionId(), e);
        }
    }
}
