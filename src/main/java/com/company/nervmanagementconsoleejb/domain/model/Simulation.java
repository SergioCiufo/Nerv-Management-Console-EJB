package com.company.nervmanagementconsoleejb.domain.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "level", column = @Column(name = "levelMin"))
})
@Table(name = "SIMULATIONS")
public class Simulation extends Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simulationId")
    private Integer simulationId;

    @OneToMany(mappedBy = "simulation")
    private List<SimulationParticipants> simulationParticipants;
}