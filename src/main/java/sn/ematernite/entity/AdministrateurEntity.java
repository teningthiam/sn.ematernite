package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("ADMINISTRATEUR")
public class AdministrateurEntity
        extends UtilisateurEntity implements Serializable {

    @Column(name = "service")
    private String service;
}