package com.company.nervmanagementconsoleejb.domain.utils;

import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.model.UserMembersStats;



public class MemberStatsAddUtils {
    public UserMembersStats createStatsMembers(User user, Member member) {
        switch (member.getName()) {
            case "Rei":
//                return new UserMembersStats(true, 0, 1, 25, 25, 50, user, member);
                return null;
            case "Asuka":
//                return new UserMembersStats(true, 0, 1, 30, 50, 25, user, member);
                return null;
            case "Shinji":
//                return new UserMembersStats(true, 0, 1, 30, 30, 30, user, member);3
                return null;
            default:
//                return new UserMembersStats(true, 0, 1, 0, 0, 0, user, member);
                return null;
        }
    }
}
