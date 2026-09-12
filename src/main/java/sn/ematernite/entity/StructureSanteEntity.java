package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "structures_sante")
public class StructureSanteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "departement")
    private String departement;

    @Column(name = "type_structure")
    private String typeStructure;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_dhis2")
    private String codeDhis2;

    @OneToMany(mappedBy = "structureSante", fetch = FetchType.LAZY)
    private List<ProfessionnelSanteEntity> professionnels = new ArrayList<>();

    @OneToMany(mappedBy = "structureSante", fetch = FetchType.LAZY)
    private List<ConsultationCPNEntity> consultations = new ArrayList<>();
}