package com.company.nervmanagementconsoleejb.application.model.dto;

import com.company.nervmanagementconsoleejb.domain.model.Activity;
import com.company.nervmanagementconsoleejb.domain.model.SimulationParticipants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SimulationDto extends ActivityDto {

    private Integer simulationId;


    private List<SimulationParticipants> simulationParticipants;
}