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

    private SoapService soapService;

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    /**
     * @PostConstruct è un'annotazione usata per eseguire codice di inizializzazione
     * DOPO che un oggetto è stato creato e tutte le dipendenze sono state injettate.
     *
     * Va usato con un metodo `init()`
     * - Garantisce che le dipendenze (@EJB) siano già pronte.
     * - Evita problemi di NullPointException o dipendenze non inizializzate.
     * - Separazione chiara tra costruzione dell'oggetto e inizializzazione.
     *
     */
    @PostConstruct
    public void init() {
        try {
            InitialContext ctx = new InitialContext();
            //percorso jndi della risorsa
            soapService = (SoapService) ctx.lookup("java:global/Nerv-Management-Console-EJB/SoapServiceImpl!com.company.nervmanagementconsoleejb.domain.service.SoapService");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Ejb non recuperato", e);
        }
    }

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
