package com.company.nervmanagementconsoleejb.domain.model;

import com.company.nervmanagementconsoleejb.domain.utils.BlobConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer idUser;
    @XmlElement(required = true, nillable = false) //soap per rendere il campo obbligatorio
    private String name;
    @XmlElement(required = true, nillable = false) //soap per rendere il campo obbligatorio
    private String surname;

    @XmlElement(required = true, nillable = false) //soap per rendere il campo obbligatorio
    @Column(unique = true, nullable = false)
    private String username;
    @XmlElement(required = true, nillable = false) //soap per rendere il campo obbligatorio
    private String password;

    @XmlTransient
    private Blob image;

    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    @Transient
    private List<Member> members;

    @XmlTransient
    @JsonIgnore
    @Transient
    private List<Simulation> simulations;

    @XmlTransient
    @JsonIgnore
    @Transient
    private List<SimulationParticipants> participants;

    @XmlTransient
    @JsonIgnore
    @Transient
    private List<Mission> missions;

    @XmlTransient
    @JsonIgnore
    @Transient
    private List<MissionParticipants> missionParticipants;

    @XmlTransient
    @JsonIgnore
    @Transient
    private List<MissionArchive> missionArchive;

//    @XmlTransient
//    @JsonIgnore
//    @Transient
//    private Map<String, List<MissionArchive>> missionArchiveResult;

    //Metodo per ottenere l'immagine in formato Base64
    @JsonProperty("image")
    //@XmlElement(name = "image")
    public String getImageAsBase64() {
        BlobConverter blobConverter = new BlobConverter();
        return blobConverter.blobToBase64(this.image);
    }

    //Metodo per impostare l'immagine da una stringa Base64
    @JsonProperty("image")
    //@XmlElement(name = "image")
    public void setImageFromBase64(String base64Image) {
        BlobConverter blobConverter = new BlobConverter();
        this.image = blobConverter.base64ToBlob(base64Image);
    }
}
