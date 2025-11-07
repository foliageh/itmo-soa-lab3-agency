package com.foliageh.repository.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Количество транспорта")
public enum Transport {
    FEW,
    NONE,
    LITTLE,
    ENOUGH
}