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
@Table(name = "consultations_cpn")
public class ConsultationCPNEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carnet_id")
    private CarnetMaternelEntity carnet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grossesse_id")
    private GrossesseEntity grossesse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professionnel_id")
    private ProfessionnelSanteEntity professionnel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "structure_sante_id")
    private StructureSanteEntity structureSante;

    @Column(name = "numero_cpn")
    private Integer numeroCPN;

    @Column(name = "date_cpn")
    private LocalDate dateCPN;

    @Column(name = "semaine_grossesse")
    private Integer semaineGrossesse;

    @Column(name = "poids")
    private Double poids;

    @Column(name = "tension_arterielle")
    private String tensionArterielle;

    @Column(name = "prochain_rdv")
    private LocalDate prochainRDV;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "synchro_dhis2")
    private Boolean synchroDhis2 = false;

    @Column(name = "statut_sync")
    private String statutSync = "SYNCHRONISE"; // EN_ATTENTE ou SYNCHRONISE
}