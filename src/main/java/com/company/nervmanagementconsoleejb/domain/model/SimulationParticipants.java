package com.company.nervmanagementconsoleejb.domain.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SIMULATION_PARTICIPANTS")
public class SimulationParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simulationParticipantId")
    private Integer simulationParticipantId;

    @ManyToOne
    @JoinColumn(name = "simulationId")
    private Simulation simulation;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}