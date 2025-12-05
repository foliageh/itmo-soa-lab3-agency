package com.foliageh.service;

import com.foliageh.controller.dto.FlatPageResponse;
import com.foliageh.controller.dto.FlatsFilterDto;
import com.foliageh.repository.entity.Flat;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AgencyService {
    private final WebClient webClient;

    public Optional<Flat> getMostExpensiveAmongThree(Integer id1, Integer id2, Integer id3) {
        List<Integer> ids = Arrays.asList(id1, id2, id3);
        List<Flat> flats = ids.stream().map(id ->
                webClient.get()
                        .uri("/flats/{id}", id)
                        .retrieve()
                        .bodyToMono(Flat.class)
                        .block()
        ).filter(Objects::nonNull).collect(Collectors.toList());
        return flats.stream().max(Comparator.comparingDouble(Flat::getPrice));
    }

    public Optional<Flat> findWithBalcony(boolean cheapest, boolean withBalcony) {
        FlatsFilterDto filter = FlatsFilterDto.builder().has_balcony(withBalcony).build();

        FlatPageResponse page = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/flats/filter")
                        .queryParam("sortBy", "price")
                        .queryParam("sortDirection", cheapest ? "asc" : "desc")
                        .queryParam("pageNumber", 0)
                        .queryParam("pageSize", 1)
                        .build()
                )
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(filter)
                .retrieve()
                .bodyToMono(FlatPageResponse.class)
                .block();

        if (page != null && !page.getFlats().isEmpty())
            return Optional.of(page.getFlats().get(0));
        return Optional.empty();
    }
}
