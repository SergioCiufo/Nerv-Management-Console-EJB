package com.company.nervmanagementconsoleejb.application.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
//@Builder
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class StatsDto {
    private Integer exp;
    private Integer level;
    private Integer synchronizationRate;
    private Integer tacticalAbility;
    private Integer supportAbility;
}