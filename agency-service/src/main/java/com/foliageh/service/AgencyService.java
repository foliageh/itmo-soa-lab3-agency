package com.foliageh.service;

import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.foliageh.repository.FlatRepository;
import com.foliageh.repository.entity.Flat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AgencyService {
    private FlatRepository flatRepository;

    public Optional<Flat> findWithBalcony(boolean cheapest, boolean withBalcony) {
        List<Flat> flats;
        if (cheapest) {
            flats = flatRepository.findByHasBalconyOrderByPriceAsc(withBalcony);
        } else {
            flats = flatRepository.findByHasBalconyOrderByPriceDesc(withBalcony);
        }
        return flats.stream().findFirst();
    }

    public Optional<Flat> getMostExpensiveAmongThree(Long id1, Long id2, Long id3) {
        List<Long> ids = Arrays.asList(id1, id2, id3);
        List<Flat> flats = flatRepository.findByIdIn(ids);
        return flats.stream().max(Comparator.comparingDouble(Flat::getPrice)).stream().findFirst();
    }
}
