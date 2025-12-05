package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "coordinates", propOrder = {"x", "y"})
public class CoordinatesSoapDto {
    @XmlElement(required = true)
    private Integer x;
    
    @XmlElement(required = true)
    private Float y;
}
