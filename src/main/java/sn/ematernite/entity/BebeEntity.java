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
@Table(name = "bebes")
public class BebeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grossesse_id", nullable = false)
    private GrossesseEntity grossesse;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "poids_naissance")
    private Double poidsNaissance;

    @Column(name = "taille_naissance")
    private Double tailleNaissance;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "apgar_score")
    private Integer apgarScore;

    @Column(name = "lieu_naissance")
    private String lieuNaissance;

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;
}