package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "find_with_balcony_request", namespace = "http://example.com/agency/schemas")
@XmlAccessorType(XmlAccessType.FIELD)
public class FindWithBalconyRequest {
    
    @XmlElement(required = true)
    private Boolean cheapest;
    
    @XmlElement(required = true)
    private Boolean with_balcony;
}