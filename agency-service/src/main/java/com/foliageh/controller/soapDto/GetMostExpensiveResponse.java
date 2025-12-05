package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "get_most_expensive_response", namespace = "http://example.com/agency/schemas")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetMostExpensiveResponse {
    
    @XmlElement
    private FlatSoapDto flat;
    
    @XmlElement
    private ErrorResponseSoapDto error;
}