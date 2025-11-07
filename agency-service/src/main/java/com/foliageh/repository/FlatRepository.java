package com.foliageh.repository;

import com.foliageh.repository.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    // Для AgencyService
    List<Flat> findByHasBalconyOrderByPriceAsc(boolean hasBalcony);
    List<Flat> findByHasBalconyOrderByPriceDesc(boolean hasBalcony);
    List<Flat> findByIdIn(List<Long> ids);
}