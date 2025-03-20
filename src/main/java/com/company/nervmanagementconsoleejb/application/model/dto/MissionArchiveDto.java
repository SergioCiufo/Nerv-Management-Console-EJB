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
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "missionArchive")
@XmlAccessorType(XmlAccessType.FIELD)
public class MissionArchiveDto {
    private Integer missionArchiveId;
    private Mission mission;
    @XmlTransient
    @JsonIgnore
    private User user;
    private Member member;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer tacticalAbility;
    private Integer synchRate;
    private Integer supportAbility;

    private MissionResultDto result;

    public enum MissionResultDto {
        WIN, LOSE, PROGRESS;
    }

}
