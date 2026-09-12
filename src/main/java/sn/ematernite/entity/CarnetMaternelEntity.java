package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;
import sn.ematernite.entity.enums.StatutCarnet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carnets_maternels")
public class CarnetMaternelEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grossesse_id", nullable = false)
    private GrossesseEntity grossesse;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutCarnet statut = StatutCarnet.EN_COURS;

    @Column(name = "groupe_sanguin")
    private String groupeSanguin;

    @Column(name = "antecedents", columnDefinition = "TEXT")
    private String antecedents;

    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultationCPNEntity> consultations = new ArrayList<>();

    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VaccinationEntity> vaccinations = new ArrayList<>();

    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamenComplementaireEntity> examens = new ArrayList<>();
}