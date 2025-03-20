package com.company.nervmanagementconsoleejb.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
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
@Entity
@Table(name = "MISSION_PARTICIPANTS")
public class MissionParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missionParticipantsId")
    private Integer missionParticipantsId;

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
}