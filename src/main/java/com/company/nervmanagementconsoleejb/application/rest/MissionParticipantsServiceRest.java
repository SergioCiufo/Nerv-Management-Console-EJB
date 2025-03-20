package com.company.nervmanagementconsoleejb.application.rest;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.MissionParticipantsDto;
import com.company.nervmanagementconsoleejb.domain.model.MissionParticipants;
import com.company.nervmanagementconsoleejb.domain.service.RestService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//da vedere
@Path("missionParticipants")
public class MissionParticipantsServiceRest {
    @EJB
    private RestService restService;

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getActiveMissions(@PathParam("userId") int userId) {
        List<MissionParticipants> listMp = restService.getActiveMissionsByUserId(userId);
        List<MissionParticipantsDto> listMpDto = mappers.toMpDto(listMp);
        if(listMpDto == null || listMpDto.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); //no content 204
        }
        return Response.ok(listMp).build();
    }

}
