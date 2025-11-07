package com.foliageh.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Ошибка при запросе")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
@Data
public class ErrorResponse {
    @JsonProperty("timestamp")
    @Schema(title = "Время ошибки", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime timestamp;
    @JsonProperty("message")
    @Schema(title = "Сообщение", requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;
    @JsonProperty("errors")
    @Schema(title = "Список ошибок", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> errors;
}