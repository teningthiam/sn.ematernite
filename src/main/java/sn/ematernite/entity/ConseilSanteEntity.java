package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conseils_sante")
public class ConseilSanteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "semaine_min")
    private Integer semaineMin;

    @Column(name = "semaine_max")
    private Integer semaineMax;

    @Column(name = "titre_fr")
    private String titreFr;

    @Column(name = "titre_wolof")
    private String titreWolof;

    @Column(name = "contenu_fr", columnDefinition = "TEXT")
    private String contenuFr;

    @Column(name = "contenu_wolof", columnDefinition = "TEXT")
    private String contenuWolof;

    @Column(name = "url_audio_fr")
    private String urlAudioFr;

    @Column(name = "url_audio_wolof")
    private String urlAudioWolof;

    @Column(name = "categorie")
    private String categorie;
}