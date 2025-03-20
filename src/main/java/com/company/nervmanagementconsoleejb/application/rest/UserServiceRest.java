package com.company.nervmanagementconsoleejb.application.rest;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.UserDto;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.service.RestService;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

//da vedere
@Path("/users")
@Stateless
public class UserServiceRest {
//    @EJB
    private RestService restService;

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    //L'annotazione ejb non funge, quindi per il momento facciamo che ci peschiamo facendo lookup
    public UserServiceRest() {
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
    public UserDto getUser(@PathParam("userId") int userId) throws SQLException {
        System.out.println("Valore Servlet Service in REST "+restService);
        User user =restService.getUserById(userId);
        return mappers.toDto(user);
    }

    //metodo con QUERY PARAMS //crea conflitto con la get normale e quindi mettiamo questa
    //http://localhost:8080/NervManagementConsoleREST/users?name=Misato
    //http://localhost:8080/NervManagementConsoleREST/users?name=Misato&surname=Katsuragi
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUsers(@QueryParam("name") String name,
                             @QueryParam("surname") String surname) {
        try {
            List<User> userList = restService.getUsersbyNameAndOrSurname(name, surname);
            List<UserDto> userDtoList = mappers.toDto(userList);
            //xml per far uscire le liste usiamo il generic
            GenericEntity<List<UserDto>> genricUserList = new GenericEntity<List<UserDto>>(userDtoList) {};
            return Response.status(200).entity(userDtoList).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/username/{username}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UserDto getUserByUsername(@PathParam("username") String username) {
        User user = restService.getUserByUsername(username);
        return mappers.toDto(user);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addUser(UserDto userDto) {
        try {
            User user = mappers.toDomain(userDto);
            restService.register(user);
            return Response.status(Response.Status.NO_CONTENT).build(); //no content 204
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            //internal servel error meglio
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateUser(UserDto userDto) {
        try {
            User user = mappers.toDomain(userDto);
            restService.updateUser(user);
            return Response.status(Response.Status.NO_CONTENT).build(); //no content 204
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{userId}/delete")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteUser(@PathParam("userId") int userId) {
        try {
            restService.removeUser(userId);
            return Response.status(Response.Status.NO_CONTENT).build(); //no content 204
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

}
