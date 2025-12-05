package com.foliageh.controller;

import com.foliageh.controller.soapDto.*;
import com.foliageh.repository.entity.Flat;
import com.foliageh.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Endpoint
@RequiredArgsConstructor
public class AgencySoapEndpoint {
    
    private static final String NAMESPACE_URI = "http://example.com/agency/schemas";
    private final AgencyService agencyService;
    private final FlatMapper flatMapper;
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "FindWithBalconyRequest")
    @ResponsePayload
    public FindWithBalconyResponse findWithBalcony(@RequestPayload FindWithBalconyRequest request) {
        FindWithBalconyResponse response = new FindWithBalconyResponse();
        
        try {
            Optional<Flat> flat = agencyService.findWithBalcony(
                request.getCheapest(), 
                request.getWithBalcony()
            );
            
            if (flat.isPresent()) {
                response.setFlat(flatMapper.toSoapDto(flat.get()));
            } else {
                response.setError(ErrorResponseSoapDto.builder()
                    .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                    .message("Квартира с указанными параметрами не найдена")
                    .errors(Arrays.asList(
                        "Не найдено квартир с балконом: " + request.getWithBalcony() +
                        " и критерием цены: " + (request.getCheapest() ? "дешевые" : "дорогие")
                    ))
                    .build());
            }
        } catch (Exception e) {
            response.setError(ErrorResponseSoapDto.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .message("Внутренняя ошибка сервера при поиске квартиры")
                .errors(Arrays.asList(e.getMessage()))
                .build());
        }
        
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMostExpensiveRequest")
    @ResponsePayload
    public GetMostExpensiveResponse getMostExpensive(@RequestPayload GetMostExpensiveRequest request) {
        GetMostExpensiveResponse response = new GetMostExpensiveResponse();
        
        try {
            Optional<Flat> flat = agencyService.getMostExpensiveAmongThree(
                request.getId1(),
                request.getId2(),
                request.getId3()
            );
            
            if (flat.isPresent()) {
                response.setFlat(flatMapper.toSoapDto(flat.get()));
            } else {
                response.setError(ErrorResponseSoapDto.builder()
                    .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                    .message("Не удалось найти одну или несколько квартир для сравнения")
                    .errors(Arrays.asList(
                        "Проверьте существование квартир с ID: " + 
                        request.getId1() + ", " + request.getId2() + ", " + request.getId3()
                    ))
                    .build());
            }
        } catch (Exception e) {
            response.setError(ErrorResponseSoapDto.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .message("Внутренняя ошибка сервера при сравнении квартир")
                .errors(Arrays.asList(e.getMessage()))
                .build());
        }
        
        return response;
    }
}
