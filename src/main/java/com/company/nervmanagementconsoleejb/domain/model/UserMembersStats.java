package com.company.nervmanagementconsoleejb.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "userMembersStats")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "level", column = @Column(name = "levelPg"))
})
@Table(name = "USERMEMBERS_STATS")
public class UserMembersStats extends Stats implements Levelable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMemberStats")
    private Integer idMemberStats;

    @XmlTransient //questa annotazione non fa vedere in xml questo attributo come risultato rest
    @JsonIgnore //questa annotazione non fa vedere in json questo attributo come risultato rest
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private Boolean status;
}