package sn.ematernite.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatutCarnet {
    EN_COURS("Carnet en cours"),
    CLOTURE("Carnet clôturé"),
    ARCHIVE("Carnet archivé");

    private final String description;
}