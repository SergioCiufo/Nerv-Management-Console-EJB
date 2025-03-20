package com.company.nervmanagementconsoleejb.application.rest;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.MissionDto;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.service.RestService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//da vedere
@Path("/missions")
public class MissionServiceRest {
    @EJB
    private RestService restService;

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<MissionDto> getMissions(){
        List<Mission> missionList = restService.retrieveMissions();
        return mappers.toMissionDto(missionList);
        //andrebbe gestita l'eccezione
        //wrapping ecczione runtime
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addMission(MissionDto missionDto) {
        try {
            Mission mission = mappers.toDomain(missionDto);
            restService.addMission(mission);
            return Response.status(Response.Status.NO_CONTENT).build(); //no content 204
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build(); //bad request ma andrebbe ampliato l'errore //tipo missioone già esistente con questo username
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateMission(MissionDto missionDto) {
        try {
            Mission mission = mappers.toDomain(missionDto);
            restService.updateMission(mission);
            return Response.status(Response.Status.NO_CONTENT).build(); //no content 204
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build(); //bad request ma andrebbe ampliato l'errore //tipo missioone già esistente con questo username
        }

    }

}