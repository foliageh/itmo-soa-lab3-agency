package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "flat", namespace = "http://example.com/agency/schemas")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "creation_date", "name", "coordinates", "area",
                      "number_of_rooms", "living_space", "price", "has_balcony",
                      "furnish", "transport", "house"})
public class FlatSoapDto {
    
    @XmlElement(required = true)
    private Integer id;
    
    @XmlElement(required = true)
    private String creation_date;
    
    @XmlElement(required = true)
    private String name;
    
    @XmlElement(required = true)
    private CoordinatesSoapDto coordinates;
    
    @XmlElement
    private Integer area;
    
    @XmlElement
    private Integer number_of_rooms;
    
    @XmlElement(required = true)
    private Double living_space;
    
    @XmlElement
    private Integer price;
    
    @XmlElement(required = true)
    private Boolean has_balcony;
    
    @XmlElement
    private String furnish;
    
    @XmlElement
    private String transport;
    
    @XmlElement
    private HouseSoapDto house;
}
