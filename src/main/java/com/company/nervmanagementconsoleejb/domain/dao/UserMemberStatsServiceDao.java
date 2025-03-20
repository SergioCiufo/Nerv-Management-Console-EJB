package com.company.nervmanagementconsoleejb.domain.dao;

import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.model.UserMembersStats;
import javax.ejb.Local;

import java.util.List;

@Local
public interface UserMemberStatsServiceDao {
    public void create(UserMembersStats userMembersStats);
    public UserMembersStats retrieveStatsByUserAndMember(User user, Member member);
    public void updateMembStatsStartSim(User user, Member member);
    public void updateMembStatsCompletedSim(User user, Member member, UserMembersStats ums);
    public void updateMembStatsCompletedMission(UserMembersStats ums);
    public List<UserMembersStats> retrieveByUserId(int userId);
}
