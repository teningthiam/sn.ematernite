package sn.ematernite.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Specialite {
    SAGE_FEMME("Sage-femme"),
    MEDECIN_GENERALISTE("Médecin généraliste"),
    GYNECOLOGUE("Gynécologue"),
    INFIRMIERE("Infirmière");

    private final String description;
}