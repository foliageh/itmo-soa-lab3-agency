package com.foliageh.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.foliageh.controller.dto.ErrorResponse;
import com.foliageh.repository.entity.Flat;
import com.foliageh.service.AgencyService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/agency")
@Tag(name = "Agency API", description = "API для выполнения части операций агенства недвижимости")
@AllArgsConstructor
public class AgencyController {
    private AgencyService agencyService;

    @Operation(summary = "Найти самую дешёвую/дорогую квартиру с балконом/без балкона")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Квартира найдена",
                    content = @Content(schema = @Schema(implementation = Flat.class))),
            @ApiResponse(responseCode = "404", description = "Квартира не найдена",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/find-with-balcony/{cheapest}/{withBalcony}")
    public ResponseEntity<?> findWithBalcony(@PathVariable Boolean cheapest, @PathVariable Boolean withBalcony) {
        try {
            Optional<Flat> flat = agencyService.findWithBalcony(cheapest, withBalcony);
            if (flat.isPresent()) {
                return ResponseEntity.ok(flat.get());
            } else {
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .message("Квартира с указанными параметрами не найдена")
                        .errors(List.of("Не найдено квартир с балконом: " + withBalcony +
                                " и критерием цены: " + (cheapest ? "дешевые" : "дорогие")))
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .message("Внутренняя ошибка сервера при поиске квартиры")
                    .errors(List.of(e.getMessage()))
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Выбрать наиболее дорогую квартиру из трёх")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Квартира найдена",
                    content = @Content(schema = @Schema(implementation = Flat.class))),
            @ApiResponse(responseCode = "404", description = "Одна или несколько квартир не найдены",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/get-most-expensive/{id1}/{id2}/{id3}")
    public ResponseEntity<?> getMostExpensive(@PathVariable Long id1, @PathVariable Long id2, @PathVariable Long id3) {
        try {
            Optional<Flat> flat = agencyService.getMostExpensiveAmongThree(id1, id2, id3);
            if (flat.isPresent()) {
                return ResponseEntity.ok(flat.get());
            } else {
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .message("Не удалось найти одну или несколько квартир для сравнения")
                        .errors(List.of("Проверьте существование квартир с ID: " + id1 + ", " + id2 + ", " + id3))
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .message("Внутренняя ошибка сервера при сравнении квартир")
                    .errors(List.of(e.getMessage()))
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
