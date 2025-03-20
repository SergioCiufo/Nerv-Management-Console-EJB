package com.company.nervmanagementconsoleejb.application.rest;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.UserMembersStatsDto;
import com.company.nervmanagementconsoleejb.domain.model.UserMembersStats;
import com.company.nervmanagementconsoleejb.domain.service.RestService;

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
@Stateless
@Path("/userMembers")
public class UserMembersStatsServiceRest {
//    @EJB
    private RestService restService;

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    public UserMembersStatsServiceRest() {
        try {
            InitialContext ctx = new InitialContext();
            //percorso jndi della risorsa
            restService = (RestService) ctx.lookup("java:global/Nerv-Management-Console-EJB/RestServiceImpl!com.company.nervmanagementconsoleejb.domain.service.RestService");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Ejb non recuperato", e);
        }
    }

    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserMembers(@PathParam("userId") int userId) { //tipo response per personalizzarci la risposta di errore 204 ex
        List<UserMembersStats> listUsm = restService.retrieveByUserId(userId);
        List<UserMembersStatsDto> listUmsDto = mappers.toUmsDto(listUsm);
        if(listUmsDto == null || listUmsDto.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build(); //no content 204
        }
        return Response.ok(listUsm).build();
    }

}
