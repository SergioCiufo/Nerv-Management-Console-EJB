package com.company.nervmanagementconsoleejb.application.model.dto;

import com.company.nervmanagementconsoleejb.domain.model.UserMembersStats;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MemberDto {
    private Integer idMember;
    private String name;
    private String surname;
    private String alias;
    @Transient
    @Getter
    @Setter //transient blocca il @Data
    private UserMembersStats userMembersStats;
}