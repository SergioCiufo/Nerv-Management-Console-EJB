package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.MemberServiceDao;
import com.company.nervmanagementconsoleejb.domain.dao.UserMemberStatsServiceDao;
import com.company.nervmanagementconsoleejb.domain.dao.UserServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.model.UserMembersStats;
import com.company.nervmanagementconsoleejb.domain.utils.MemberStatsAddUtils;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import java.util.List;

@Stateless
public class RegisterService {
    @EJB
    private MemberServiceDao memberServiceDao;

    @EJB
    private UserServiceDao userServiceDao;

    @EJB
    private UserMemberStatsServiceDao userMemberStatsServiceDao;

    @EJB
    private MemberStatsAddUtils memberStatsAddUtils;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void register(User newUser) {
        List<Member> defaultMemberList = memberServiceDao.retrieveMembers();
        userServiceDao.create(newUser);
        newUser.setIdUser(userServiceDao.getUserByUsername(newUser.getUsername()).getIdUser());
        for(Member member : defaultMemberList){
            if(member.getIdMember() != null){
                UserMembersStats stats = memberStatsAddUtils.createStatsMembers(newUser, member);
                userMemberStatsServiceDao.create(stats);
                member.setUserMembersStats(stats);
            }
        }
    }

}
