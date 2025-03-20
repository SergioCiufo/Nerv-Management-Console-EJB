package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.UserMemberStatsServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.model.UserMembersStats;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;

@Stateless
public class UserMemberStatsService {
    @EJB
    private UserMemberStatsServiceDao userMemberStatsServiceDao;

    public UserMembersStats retrieveStatsByUserAndMember(User user, Member member) {
        return userMemberStatsServiceDao.retrieveStatsByUserAndMember(user, member);
    }

    public void updateMembStatsStartSim(User user, Member member) {
        userMemberStatsServiceDao.updateMembStatsStartSim(user, member);
    }

    public void updateMembStatsCompletedSim(User user, Member member, UserMembersStats ums) {
        userMemberStatsServiceDao.updateMembStatsCompletedSim(user, member, ums);
    }

    public void updateMembStatsCompletedMission(UserMembersStats ums) {
        userMemberStatsServiceDao.updateMembStatsCompletedMission(ums);
    }

    public List<UserMembersStats> retrieveByUserId(int userId){
        return userMemberStatsServiceDao.retrieveByUserId(userId);
    }

}