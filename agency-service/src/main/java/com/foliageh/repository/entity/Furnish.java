package com.foliageh.repository.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Обустройство дома")
public enum Furnish {
    DESIGNER,
    NONE,
    FINE,
    BAD,
    LITTLE
}