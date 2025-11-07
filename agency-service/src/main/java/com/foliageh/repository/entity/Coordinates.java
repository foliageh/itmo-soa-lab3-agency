package com.foliageh.repository.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter(value = AccessLevel.PACKAGE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    @NotNull
    @DecimalMin(value = "-12.999", inclusive = true)
    private Long x;

    @NotNull
    @DecimalMin(value = "-732.999", inclusive = true)
    private Float y;
}