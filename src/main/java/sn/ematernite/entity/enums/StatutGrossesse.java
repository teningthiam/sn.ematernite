package sn.ematernite.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatutGrossesse {
    EN_COURS("Grossesse en cours"),
    TERMINEE("Grossesse terminée"),
    PERDUE("Grossesse perdue");

    private final String description;
}