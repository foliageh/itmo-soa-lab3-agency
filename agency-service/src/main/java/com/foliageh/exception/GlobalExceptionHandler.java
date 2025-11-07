package com.foliageh.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        var body = new HashMap<String, Object>();
        body.put("timestamp", LocalDateTime.now());

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {

        var body = new HashMap<String, Object>();
        body.put("timestamp", LocalDateTime.now());

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {

            if (invalidFormatException.getTargetType().isEnum()) {
                var fieldName = invalidFormatException.getPath().getFirst().getFieldName();
                var receivedValue = invalidFormatException.getValue().toString();

                var enumConstants = invalidFormatException.getTargetType().getEnumConstants();
                var enumValues = Arrays.stream(enumConstants)
                        .map(obj -> ((Enum<?>) obj).name())
                        .toArray(String[]::new);

                var errorMessage = String.format(
                        "Поле '%s' имеет недопустимое значение '%s'. Допустимые значения: %s",
                        fieldName, receivedValue, String.join(", ", enumValues)
                );

                body.put("error", errorMessage);
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
            }
        }

        body.put("error", "Неверный формат JSON запроса");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}