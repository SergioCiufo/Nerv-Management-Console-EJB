package com.company.nervmanagementconsoleejb.infrastructure.atomicDao;

import com.company.nervmanagementconsoleejb.domain.exception.DatabaseException;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;

import java.util.List;

@Stateless
public class UserDaoAtomic {

    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    public void create(User user) {
        try {
            entityManager.persist(user);
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error create user " + user.getUsername(), e);
        }

    }

    public User getUserByUsername(String username) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (HibernateException e) {
            throw new DatabaseException("Unexpected error during retrieval by username: " + username, e);
        }
    }

    public List<User> retrieve(){
        try {
            return entityManager.createQuery("from User order by idUser ASC", User.class).getResultList();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error during retrieval", e);
        }
    }

    public User getUserById(int userId) {
        try {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.idUser = :userId", User.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (HibernateException e) {
            throw new DatabaseException("Unexpected error during retrieval user by id: " + userId, e);
        }
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            return entityManager.createQuery("FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (HibernateException e) {
            throw new DatabaseException("Unexpected error during retrieval by username: " + username, e);
        }
    }

    public void updateUser(User user) {
        try{
            entityManager.merge(user);
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error update user " + user.getIdUser(), e);
        }
    }

    public void removeUser(int userId) {
        try {
            //prossima volta cascata sul model, anziché ammazzarsi così
            entityManager
                    .createQuery("DELETE FROM UserMembersStats ums WHERE ums.user.idUser = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();

//            entityManager
//                    .createQuery("DELETE FROM MissionArchive ma WHERE ma.user.idUser = :userId")
//                    .setParameter("userId", userId)
//                    .executeUpdate();

            entityManager
                    .createQuery("DELETE FROM MissionParticipants ms WHERE ms.user.idUser = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();

            entityManager
                    .createQuery("DELETE FROM SimulationParticipants sp WHERE sp.user.idUser = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();

            entityManager
                    .createQuery("DELETE FROM User u " +
                            "WHERE u.idUser = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error removing user " + userId, e);
        }
    }

}
