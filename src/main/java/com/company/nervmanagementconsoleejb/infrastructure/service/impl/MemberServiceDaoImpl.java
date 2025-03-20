package com.company.nervmanagementconsoleejb.infrastructure.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MemberServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.infrastructure.atomicDao.MemberDaoAtomic;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;
//da controllare l'injezione
@Stateless
public class MemberServiceDaoImpl implements MemberServiceDao {
    @EJB
    private MemberDaoAtomic memberDaoAtomic;

    @Override
    public List<Member> retrieveMembers() {
        return memberDaoAtomic.retrieve();
    }

    @Override
    public Member retrieveByMemberId(int idMember) {
        return memberDaoAtomic.retrieveByMemberId(idMember);
    }
}
