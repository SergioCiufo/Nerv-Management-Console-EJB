package com.company.nervmanagementconsoleejb.application.model.dto;

import com.company.nervmanagementconsoleejb.domain.model.*;
import com.company.nervmanagementconsoleejb.domain.utils.BlobConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.xml.bind.annotation.*;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDto {
    private Integer idUser;
    @XmlElement(required = true, nillable = false) //soap per rendere il campo obbligatorio
    private String name;
    @XmlElement(required = true, nillable = false) //soap per rendere il campo obbligatorio
    private String surname;
    @XmlElement(required = true, nillable = false) //soap per rendere il campo obbligatorio
    private String username;
    @XmlElement(required = true, nillable = false) //soap per rendere il campo obbligatorio
    private String password;

    @XmlTransient
    private Blob image;

    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    @Transient
    private List<Member> members;

    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    @Transient
    private List<Simulation> simulations;

    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    @Transient
    private List<SimulationParticipants> participants;

    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    @Transient
    private List<Mission> missions;

    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    @Transient
    private List<MissionParticipants> missionParticipants;

    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    @Transient
    private List<MissionArchive> missionArchive;

//momentaneo
//@XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
//@JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
//@Transient
//    private Map<String, List<MissionArchive>> missionArchiveResult;

    //Metodo per impostare l'immagine da una stringa Base64
    @JsonProperty("image")
    //@XmlElement(name = "image")
    public void setImageFromBase64(String base64Image) {
        BlobConverter blobConverter = new BlobConverter();
        this.image = blobConverter.base64ToBlob(base64Image);
    }
}

