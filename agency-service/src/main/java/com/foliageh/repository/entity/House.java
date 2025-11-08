package com.foliageh.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
@Getter
@Setter(value = AccessLevel.PACKAGE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House {
    @JsonProperty("name")
    @Column(name = "name")
    @Schema(title = "Имя дома", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name; // Поле может быть null

    @JsonProperty("year")
    @Column(name = "year", nullable = false)
    @Schema(title = "Год", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Год не может быть null")
    @Min(value = 1, message = "Год должен быть больше 0")
    @Max(value = 2025, message = "Год не может превышать 370")
    private Integer year;

    @JsonProperty("number_of_floors")
    @Column(name = "number_of_floors", nullable = false)
    @Schema(title = "Количество этажей", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Количество этажей не может быть null")
    @Positive(message = "Количество этажей должно быть положительным числом")
    @Min(value = 1, message = "Количество этажей должно быть не менее 1")
    private Integer numberOfFloors;

    @JsonProperty("number_of_lifts")
    @Column(name = "number_of_lifts", nullable = false)
    @Schema(title = "Количество лифтов", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Количество лифтов не может быть null")
    @Positive(message = "Количество лифтов должно быть положительным числом")
    @Min(value = 1, message = "Количество лифтов должно быть не менее 1")
    private Integer numberOfLifts;
}
