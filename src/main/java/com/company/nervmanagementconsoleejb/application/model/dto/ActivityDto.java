package com.company.nervmanagementconsoleejb.application.model.dto;

import javax.persistence.MappedSuperclass;

import com.company.nervmanagementconsoleejb.domain.model.Stats;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
//@Builder
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class ActivityDto extends StatsDto {
    protected String name;
    protected Integer durationTime;
}
