package sn.ematernite.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TypeNotification {
    RAPPEL_RDV("Rappel rendez-vous CPN"),
    CONSEIL_SEMAINE("Conseil de la semaine"),
    ALERTE_URGENCE("Alerte urgence");

    private final String description;
}