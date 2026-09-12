package sn.ematernite.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    FEMME_ENCEINTE("Femme enceinte"),
    SAGE_FEMME("Sage-femme"),
    MEDECIN("Médecin"),
    ADMINISTRATEUR("Administrateur");

    private final String description;
}