package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaccinations")
public class VaccinationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carnet_id")
    private CarnetMaternelEntity carnet;

    @Column(name = "type_vaccin")
    private String typeVaccin;

    @Column(name = "date_vaccin")
    private LocalDate dateVaccin;

    @Column(name = "centre_vaccination")
    private String centreVaccination;

    @Column(name = "observations")
    private String observations;
}