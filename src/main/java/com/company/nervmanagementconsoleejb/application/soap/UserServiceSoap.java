package com.company.nervmanagementconsoleejb.application.soap;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.UserDto;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.service.SoapService;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.util.List;


//http://localhost:8080/Nerv-Management-Console-EJB/UserServiceSoap?wsdl
@WebService
public class UserServiceSoap {

    @EJB
    private SoapService soapService;

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    @WebMethod(operationName = "usersList")
    public List<UserDto> usersList() throws Exception {
        try {
            System.out.println(soapService);
            List<User> userList = soapService.getUsersList();
            return mappers.toDto(userList);
        }catch(NullPointerException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            return null;
        }
    }

    @WebMethod(operationName = "addUser")
    public Response addUser(@WebParam(name = "user") UserDto userDto) {
        try {
            User user = mappers.toDomain(userDto);
            soapService.register(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @WebMethod(operationName = "updateUser")
    public Response updateUser(@WebParam(name = "user") UserDto userDto) {
        try {
            User user = mappers.toDomain(userDto);
            soapService.updateUser(user);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @WebMethod(operationName = "deleteUser")
    public Response deleteUser(@WebParam(name = "userId") int userId) {
        try {
            soapService.removeUser(userId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
