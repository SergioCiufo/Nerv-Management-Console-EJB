package com.company.nervmanagementconsoleejb.domain.dao;

import com.company.nervmanagementconsoleejb.domain.model.Member;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MemberServiceDao {
    public List<Member> retrieveMembers();
    public Member retrieveByMemberId(int idMember);
}
