package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("FEMME_ENCEINTE")
public class FemmeEnceinteEntity
        extends UtilisateurEntity implements Serializable {

    @Column(name = "date_naissance")
    private String dateNaissance;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_qr", unique = true)
    private String codeQR;

    @OneToMany(mappedBy = "femmeEnceinte",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GrossesseEntity> grossesses = new ArrayList<>();

    @OneToMany(mappedBy = "femmeEnceinte",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationEntity> notifications = new ArrayList<>();
}