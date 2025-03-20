package com.company.nervmanagementconsoleejb.infrastructure.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.UserMemberStatsServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.model.UserMembersStats;
import com.company.nervmanagementconsoleejb.infrastructure.atomicDao.UserMemberStatsDaoAtomic;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;
//da controllare l'injezione
@Stateless
public class UserMemberStatsServiceDaoImpl implements UserMemberStatsServiceDao {
    @EJB
    private UserMemberStatsDaoAtomic userMemberStatsDaoAtomic;


    @Override
    public void create(UserMembersStats userMembersStats) {
        userMemberStatsDaoAtomic.create(userMembersStats);
    }

    @Override
    public UserMembersStats retrieveStatsByUserAndMember(User user, Member member) {
        return userMemberStatsDaoAtomic.retrieveByUserAndMember(user, member);
    }

    @Override
    public void updateMembStatsStartSim(User user, Member member) {
        userMemberStatsDaoAtomic.updateMemStatsStartSim(user, member);
    }

    @Override
    public void updateMembStatsCompletedSim(User user, Member member, UserMembersStats ums) {
        userMemberStatsDaoAtomic.updateMembStatsCompletedSim(user, member, ums);
    }

    @Override
    public void updateMembStatsCompletedMission(UserMembersStats ums) {
        userMemberStatsDaoAtomic.updateMembStatsCompletedMission(ums);
    }

    @Override
    public List<UserMembersStats> retrieveByUserId(int userId) {
        return userMemberStatsDaoAtomic.retrieveByUserId(userId);
    }
}
