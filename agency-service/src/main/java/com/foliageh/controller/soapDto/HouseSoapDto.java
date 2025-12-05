package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "House", propOrder = {"name"})
public class HouseSoapDto {
    @XmlElement
    private String name;
}
