package com.foliageh.controller.dto;

import com.foliageh.repository.entity.Flat;

public class FlatMapper {
    public static Flat mapFlatFromFlatDto(FlatDto flatDto) {
        return Flat.builder()
                .house(flatDto.getHouse())
                .area(flatDto.getArea())
                .name(flatDto.getName())
                .furnish(flatDto.getFurnish())
                .coordinates(flatDto.getCoordinates())
                .livingSpace(flatDto.getLivingSpace())
                .numberOfRooms(flatDto.getNumberOfRooms())
                .transport(flatDto.getTransport())
                .build();
    }
}
