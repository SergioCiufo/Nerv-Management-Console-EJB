package com.company.nervmanagementconsoleejb.application.model.dto;


import com.company.nervmanagementconsoleejb.domain.model.Levelable;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.Stats;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "userMembersStats")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserMembersStatsDto extends Stats implements Levelable {
    private Integer idMemberStats;
    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    private User user;
    private Member member;
    private Boolean status;
}