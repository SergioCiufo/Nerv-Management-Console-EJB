package com.company.nervmanagementconsoleejb.application;

import com.company.nervmanagementconsoleejb.application.model.dto.*;
import com.company.nervmanagementconsoleejb.domain.model.*;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface Mappers {

    User toDomain (UserDto userDto);
    UserDto toDto(User user);

    List<UserDto> toDto(List<User> userList);

    ///usare superbuilder sulla classe e sulle classi padri (anche se astratte)
    @Mappings({
            @Mapping(target = "exp", source = "exp"),
            @Mapping(target = "level", source = "level"),
            @Mapping(target = "synchronizationRate", source = "synchronizationRate"),
            @Mapping(target = "tacticalAbility", source = "tacticalAbility"),
            @Mapping(target = "supportAbility", source = "supportAbility"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "durationTime", source = "durationTime"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "participantsMax", source = "participantsMax"),
            @Mapping(target = "eventMission", source = "eventMission"),
            @Mapping(target = "available", source = "available"),
            @Mapping(target = "releaseDate", source = "releaseDate")
    })
    MissionDto toDto(Mission mission);
    /// inverte come visto sopra
    @InheritInverseConfiguration
    Mission toDomain(MissionDto missionDto);


    @Mappings({
            @Mapping(target = "exp", source = "exp"),
            @Mapping(target = "level", source = "level"),
            @Mapping(target = "synchronizationRate", source = "synchronizationRate"),
            @Mapping(target = "tacticalAbility", source = "tacticalAbility"),
            @Mapping(target = "supportAbility", source = "supportAbility"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "durationTime", source = "durationTime"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "participantsMax", source = "participantsMax"),
            @Mapping(target = "eventMission", source = "eventMission"),
            @Mapping(target = "available", source = "available"),
            @Mapping(target = "releaseDate", source = "releaseDate")
    })
    List<MissionDto> toMissionDto(List<Mission> missionList);
    @InheritInverseConfiguration
    List<Mission> toDomain(List<MissionDto> missionDtoList);


    List<UserMembersStatsDto> toUmsDto(List<UserMembersStats> userMembersStats);

    List<MissionParticipantsDto> toMpDto(List<MissionParticipants> missionParticipants);

    List<MissionArchiveDto> toMaDto(List<MissionArchive> missionArchiveList);
}
