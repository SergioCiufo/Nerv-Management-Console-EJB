package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MemberServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import javax.ejb.EJB;
import javax.ejb.Stateless;


import java.util.List;

@Stateless
public class MemberService {
    @EJB
    private MemberServiceDao memberServiceDao;

    public List<Member> retrieveMembers() {
        return memberServiceDao.retrieveMembers();
    }

    public Member retrieveByMemberId (int idMember) {
        return memberServiceDao.retrieveByMemberId(idMember);
    }

}