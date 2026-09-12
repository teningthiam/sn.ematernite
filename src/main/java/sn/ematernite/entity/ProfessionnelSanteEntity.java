package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import sn.ematernite.entity.enums.Specialite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("PROFESSIONNEL_SANTE")
public class ProfessionnelSanteEntity
        extends UtilisateurEntity implements Serializable {

    @Column(name = "matricule", unique = true)
    private String matricule;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialite")
    private Specialite specialite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "structure_sante_id")
    private StructureSanteEntity structureSante;

    @OneToMany(mappedBy = "professionnel",
            cascade = CascadeType.ALL)
    private List<ConsultationCPNEntity> consultations = new ArrayList<>();
}