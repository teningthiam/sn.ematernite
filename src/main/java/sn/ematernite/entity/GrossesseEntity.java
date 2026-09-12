package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;
import sn.ematernite.entity.enums.StatutGrossesse;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grossesses")
public class GrossesseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "femme_enceinte_id", nullable = false)
    private FemmeEnceinteEntity femmeEnceinte;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "dpa", nullable = false)
    private LocalDate dpa;

    @Column(name = "numero_grossesse")
    private Integer numeroGrossesse = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutGrossesse statut = StatutGrossesse.EN_COURS;

    @OneToOne(mappedBy = "grossesse", cascade = CascadeType.ALL)
    private CarnetMaternelEntity carnet;

    @OneToOne(mappedBy = "grossesse", cascade = CascadeType.ALL)
    private BebeEntity bebe;

    @OneToMany(mappedBy = "grossesse", cascade = CascadeType.ALL)
    private List<ConsultationCPNEntity> consultations = new ArrayList<>();

    // Méthode métier : calcul automatique de la semaine
    public int calculerSemaine() {
        return (int) ChronoUnit.WEEKS.between(dateDebut, LocalDate.now());
    }

    public long getJoursRestants() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dpa);
    }
}