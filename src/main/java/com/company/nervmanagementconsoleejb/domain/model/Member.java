package com.company.nervmanagementconsoleejb.domain.model;

import javax.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEMBERS")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Integer idMember;
    private String name;
    private String surname;
    private String alias;
    @Transient
    @Getter @Setter //transient blocca il @Data
    private UserMembersStats userMembersStats;
}