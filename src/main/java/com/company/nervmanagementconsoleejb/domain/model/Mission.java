package com.company.nervmanagementconsoleejb.domain.model;

import com.company.nervmanagementconsoleejb.domain.utils.BlobConverter;
import com.company.nervmanagementconsoleejb.domain.utils.BooleanToCharConverterUtils;
import com.company.nervmanagementconsoleejb.domain.utils.LocalDateAdapterUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "level", column = @Column(name = "levelMin"))
})
@Table(name = "MISSION")
public class Mission extends Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missionId")
    private Integer missionId;
    private String description;

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

    //JAXB non supporta nativamente LocalDate,
    //quindi è necessario un XmlAdapter per serializzarlo correttamente come stringa (yyyy-MM-dd) in XML.
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