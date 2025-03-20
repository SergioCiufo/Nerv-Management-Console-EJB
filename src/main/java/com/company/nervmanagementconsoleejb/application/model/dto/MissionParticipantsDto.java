package com.company.nervmanagementconsoleejb.application.model.dto;

import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
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
@XmlRootElement(name = "missionParticipants")
@XmlAccessorType(XmlAccessType.FIELD)
public class MissionParticipantsDto {
    private Integer missionParticipantsId;
    private Mission mission;
    @XmlTransient
    @JsonIgnore
    private User user;
    private Member member;
}