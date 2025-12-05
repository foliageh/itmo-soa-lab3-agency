package com.foliageh.controller;

import com.foliageh.controller.soapDto.CoordinatesSoapDto;
import com.foliageh.controller.soapDto.FlatSoapDto;
import com.foliageh.controller.soapDto.HouseSoapDto;
import com.foliageh.repository.entity.Flat;
import org.springframework.stereotype.Component;

@Component
public class FlatMapper {
    
    public FlatSoapDto toSoapDto(Flat flat) {
        if (flat == null) {
            return null;
        }
        
        FlatSoapDto dto = new FlatSoapDto();
        dto.setId(flat.getId());
        dto.setCreation_date(flat.getCreationDate() != null ? flat.getCreationDate().toString() : null);
        dto.setName(flat.getName());
        dto.setArea(flat.getArea());
        dto.setNumber_of_rooms(flat.getNumberOfRooms());
        dto.setLiving_space(flat.getLivingSpace());
        dto.setPrice(flat.getPrice());
        dto.setHas_balcony(flat.getHasBalcony());
        dto.setFurnish(flat.getFurnish() != null ? flat.getFurnish().name() : null);
        dto.setTransport(flat.getTransport() != null ? flat.getTransport().name() : null);
        
        if (flat.getCoordinates() != null) {
            CoordinatesSoapDto coords = new CoordinatesSoapDto();
            coords.setX(flat.getCoordinates().getX());
            coords.setY(flat.getCoordinates().getY());
            dto.setCoordinates(coords);
        }
        
        if (flat.getHouse() != null) {
            HouseSoapDto house = new HouseSoapDto();
            house.setName(flat.getHouse().getName());
            dto.setHouse(house);
        }
        
        return dto;
    }
}
