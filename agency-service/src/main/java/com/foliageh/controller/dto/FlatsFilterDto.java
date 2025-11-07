package com.foliageh.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.foliageh.repository.entity.Furnish;
import com.foliageh.repository.entity.Transport;

@Schema(description = "Запрос на сортировку квартир")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
@Data
public class FlatsFilterDto {
    @JsonProperty("name")
    @Schema(title = "Имя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @JsonProperty("min_area")
    @Schema(title = "минимальная площадь", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int minArea;

    @JsonProperty("max_area")
    @Schema(title = "минимальная площадь", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int maxArea;

    @JsonProperty("min_rooms")
    @Schema(title = "минимальная площадь", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int minRooms;

    @JsonProperty("max_rooms")
    @Schema(title = "максимальная площадь", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int maxRooms;

    @JsonProperty("furnish")
    @Schema(title = "фурнитруа", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Furnish furnish;

    @JsonProperty("transport")
    @Schema(title = "транспорт", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Transport transport;
}
