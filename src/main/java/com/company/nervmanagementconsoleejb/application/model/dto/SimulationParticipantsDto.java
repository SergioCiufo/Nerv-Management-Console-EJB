package com.company.nervmanagementconsoleejb.application.model.dto;

import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.Simulation;
import com.company.nervmanagementconsoleejb.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimulationParticipantsDto {

    private Integer simulationParticipantId;

    private Simulation simulation;

    private User user;

    private Member member;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}