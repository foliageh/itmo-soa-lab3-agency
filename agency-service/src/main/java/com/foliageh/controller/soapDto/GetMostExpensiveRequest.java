package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "GetMostExpensiveRequest", namespace = "http://example.com/agency/schemas")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetMostExpensiveRequest {
    
    @XmlElement(required = true)
    private Integer id1;
    
    @XmlElement(required = true)
    private Integer id2;
    
    @XmlElement(required = true)
    private Integer id3;
}