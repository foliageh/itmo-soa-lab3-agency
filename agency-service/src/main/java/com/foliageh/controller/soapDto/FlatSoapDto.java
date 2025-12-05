package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Flat", namespace = "http://example.com/agency/schemas")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "creationDate", "name", "coordinates", "area", 
                      "numberOfRooms", "livingSpace", "price", "hasBalcony", 
                      "furnish", "transport", "house"})
public class FlatSoapDto {
    
    @XmlElement(required = true)
    private Integer id;
    
    @XmlElement(required = true)
    private String creationDate;
    
    @XmlElement(required = true)
    private String name;
    
    @XmlElement(required = true)
    private CoordinatesSoapDto coordinates;
    
    @XmlElement
    private Integer area;
    
    @XmlElement
    private Integer numberOfRooms;
    
    @XmlElement(required = true)
    private Double livingSpace;
    
    @XmlElement
    private Integer price;
    
    @XmlElement(required = true)
    private Boolean hasBalcony;
    
    @XmlElement
    private String furnish;
    
    @XmlElement
    private String transport;
    
    @XmlElement
    private HouseSoapDto house;
}
