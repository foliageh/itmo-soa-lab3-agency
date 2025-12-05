package com.foliageh.controller.dto;

import com.foliageh.repository.entity.Furnish;
import com.foliageh.repository.entity.Transport;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value @Builder
public class FlatsFilterDto implements Serializable {
    String name;
    Integer min_area;
    Integer max_area;
    Integer min_rooms;
    Integer max_rooms;
    Integer min_price;
    Integer max_price;
    Furnish furnish;
    Transport transport;
    Boolean has_balcony;
}