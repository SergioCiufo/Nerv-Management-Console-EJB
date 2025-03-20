package com.company.nervmanagementconsoleejb.domain.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Stats {
    @Column(name = "exp")
    private Integer exp;
    @Column(name = "levelPg")
    private Integer level;
    @Column(name = "synchronizationRate")
    private Integer synchronizationRate;
    @Column(name = "tacticalAbility")
    private Integer tacticalAbility;
    @Column(name = "supportAbility")
    private Integer supportAbility;
}