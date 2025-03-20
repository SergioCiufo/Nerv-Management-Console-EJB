package com.company.nervmanagementconsoleejb.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

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
@Entity
@Table(name = "MISSION_ARCHIVE")
public class MissionArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missionArchiveId")
    private Integer missionArchiveId;

    @ManyToOne
    @JoinColumn(name = "missionId")
    private Mission mission;

    @XmlTransient
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer tacticalAbility;
    private Integer synchRate;
    private Integer supportAbility;

    @Enumerated(EnumType.STRING)
    private MissionResult result;

    public enum MissionResult {
        WIN, LOSE, PROGRESS;
    }

}
