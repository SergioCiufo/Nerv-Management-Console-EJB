package com.company.nervmanagementconsoleejb.application.soap;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.MissionDto;
import com.company.nervmanagementconsoleejb.domain.model.Mission;
import com.company.nervmanagementconsoleejb.domain.service.SoapService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//da vedere
@WebService
public class MissionEventServiceSoap {

    @EJB
    private SoapService soapService;

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    @WebMethod(operationName = "addMissionEvent")
    public MissionDto addMissionEvent(@WebParam(name = "mission") MissionDto missionDto) {
        try {
            Mission mission = mappers.toDomain(missionDto);
            soapService.addEventMission(mission);
            missionDto = mappers.toDto(mission);
            return missionDto;
        } catch (Exception e) {
            return null;
        }
    }
}
