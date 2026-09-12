package sn.ematernite.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Langue {
    FRANCAIS("Français"),
    WOLOF("Wolof");

    private final String description;
}