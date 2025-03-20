package com.company.nervmanagementconsoleejb.application.model.dto;

import com.company.nervmanagementconsoleejb.domain.model.Activity;
import com.company.nervmanagementconsoleejb.domain.model.MissionParticipants;
import com.company.nervmanagementconsoleejb.domain.utils.BlobConverter;
import com.company.nervmanagementconsoleejb.domain.utils.BooleanToCharConverterUtils;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.company.nervmanagementconsoleejb.domain.utils.LocalDateAdapterUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "mission")
@XmlAccessorType(XmlAccessType.FIELD)
public class MissionDto extends ActivityDto {
    private Integer missionId;
    private String description;

    @XmlTransient
    private Blob image;

    private Integer participantsMax;

    @XmlTransient
    @JsonIgnore
    @Transient
    private List<MissionParticipants> missionParticipants;

    @Convert(converter = BooleanToCharConverterUtils.class) // Y or N
    private Boolean eventMission; //true la missione è un evento speciale //false è una missione normale

    @Convert(converter = BooleanToCharConverterUtils.class) // Y or N
    private Boolean available; //true la missione è disponibile per essere giocata //false la missione non è disponibile per essere giocata

    @XmlJavaTypeAdapter(LocalDateAdapterUtils.class)
    private LocalDate releaseDate;

    @JsonProperty("image")
    @XmlElement(name = "image")
    public String getImageAsBase64() {
        BlobConverter blobConverter = new BlobConverter();
        return blobConverter.blobToBase64(this.image);
    }

    //Metodo per impostare l'immagine da una stringa Base64
    @JsonProperty("image")
    @XmlElement(name = "image")
    public void setImageFromBase64(String base64Image) {
        BlobConverter blobConverter = new BlobConverter();
        this.image = blobConverter.base64ToBlob(base64Image);
    }
}