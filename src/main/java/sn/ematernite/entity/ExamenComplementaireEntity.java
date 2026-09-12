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
@Table(name = "examens_complementaires")
public class ExamenComplementaireEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carnet_id")
    private CarnetMaternelEntity carnet;

    @Column(name = "type_examen")
    private String typeExamen;

    @Column(name = "date_examen")
    private LocalDate dateExamen;

    @Column(name = "resultat", columnDefinition = "TEXT")
    private String resultat;

    @Column(name = "laboratoire")
    private String laboratoire;
}