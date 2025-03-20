package com.company.nervmanagementconsoleejb.infrastructure.atomicDao;

import com.company.nervmanagementconsoleejb.domain.exception.DatabaseException;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.model.UserMembersStats;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;

import java.util.List;

@Stateless
public class UserMemberStatsDaoAtomic {
    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    public void create(UserMembersStats userMembersStats) {
        try {
            entityManager.persist(userMembersStats);
        }catch(HibernateException e){
            throw new DatabaseException("Unexpected error adding member to user: " + userMembersStats.getUser().getIdUser(), e);
        }
    }

    public List<UserMembersStats> retrieve(){
        try {
            return entityManager.createQuery("from UserMembersStats ums order by idMemberStats ASC", UserMembersStats.class).getResultList();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error during retrieval", e);
        }
    }

    public List<UserMembersStats> retrieveByUserId(int userId){
        try {
            return entityManager.createQuery("from UserMembersStats ums WHERE ums.user.id =:userId", UserMembersStats.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error during retrieval by userId" + userId, e);
        }
    }

    public UserMembersStats retrieveByUserAndMember(User user, Member member){
        try{
            return entityManager.createQuery("FROM UserMembersStats ums WHERE ums.user.id = :userId AND ums.member.id = :memberId ", UserMembersStats.class)
                    .setParameter("userId", user.getIdUser())
                    .setParameter("memberId", member.getIdMember())
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error retrieving user and member by userId: " +user.getIdUser() , e);
        }
    }

    public UserMembersStats retrieveByUserAndMemberId(User user, Integer memberId){
        try{
            return entityManager.createQuery("FROM UserMembersStats ums "
                            + "WHERE ums.user.id = :userId AND ums.member.id = :memberId ", UserMembersStats.class)
                    .setParameter("userId", user.getIdUser())
                    .setParameter("memberId", memberId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error retrieving by userId: " +user.getIdUser() + " and MemberId: "+memberId , e);
        }
    }

    public void updateMemStatsStartSim(User user, Member member) {
        try{
            entityManager.createQuery("UPDATE UserMembersStats ums "
                            + "SET ums.status = :status "
                            + "WHERE ums.user.id = :userId AND ums.member.id = :memberId")
                    .setParameter("status", false)
                    .setParameter("userId", user.getIdUser())
                    .setParameter("memberId", member.getIdMember())
                    .executeUpdate();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error updating MemberStats by userId: " +user.getIdUser() + "member: "+member.getIdMember() , e);
        }
    }

    public void updateMembStatsCompletedSim(User user, Member member, UserMembersStats ums) {
        try{
            entityManager.createQuery("UPDATE UserMembersStats ums "
                            + "SET ums.status = :status, ums.exp = :exp, ums.level = :levelPg, " +
                            "ums.synchronizationRate = :sincRate, ums.tacticalAbility = :tactAbility, " +
                            "ums.supportAbility = :suppAbility "
                            + "WHERE ums.user.id = :userId AND ums.member.id = :memberId")
                    .setParameter("status", true)
                    .setParameter("exp", ums.getExp())
                    .setParameter("levelPg", ums.getLevel())
                    .setParameter("sincRate", ums.getSynchronizationRate())
                    .setParameter("tactAbility", ums.getTacticalAbility())
                    .setParameter("suppAbility", ums.getSupportAbility())
                    .setParameter("userId", user.getIdUser())
                    .setParameter("memberId", member.getIdMember())
                    .executeUpdate();
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error updating MemberStats by userId: " +user.getIdUser() + "member: "+member.getIdMember() , e);
        }
    }

    public void updateMembStatsCompletedMission(UserMembersStats uMemberStats){
        try{
            entityManager.merge(uMemberStats);
        }catch (HibernateException e){
            throw new DatabaseException("Unexpected error updating MemberStats by Id: " +uMemberStats.getIdMemberStats() , e);
        }
    }
}
