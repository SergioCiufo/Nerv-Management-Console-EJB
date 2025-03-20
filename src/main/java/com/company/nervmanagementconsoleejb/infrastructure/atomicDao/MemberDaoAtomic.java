package com.company.nervmanagementconsoleejb.infrastructure.atomicDao;

import com.company.nervmanagementconsoleejb.domain.exception.DatabaseException;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;

import java.util.List;

@Stateless
public class MemberDaoAtomic {
    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    public List<Member> retrieve(){
        try {
            return entityManager.createQuery("from Member m order by idMember ASC", Member.class).getResultList();
        }catch (HibernateException e ){
            throw new DatabaseException("Unexpected error during retrieval", e);
        }
    }

    public Member retrieveByMemberId(int idMember) {
        try {
            return entityManager.createQuery("FROM Member m WHERE m.idMember = :memberId", Member.class)
                    .setParameter("memberId", idMember)
                    .getSingleResult();
        }catch(NoResultException e){
            return null;
        }catch(HibernateException e ){
            throw new DatabaseException("Error retrieving member: " + idMember + " ", e);
        }
    }
}
