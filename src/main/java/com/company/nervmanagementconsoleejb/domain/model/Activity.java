package com.company.nervmanagementconsoleejb.domain.model;

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
public abstract class Activity extends Stats {
    protected String name;
    protected Integer durationTime;
}
