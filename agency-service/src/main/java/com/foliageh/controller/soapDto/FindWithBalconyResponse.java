package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "FindWithBalconyResponse", namespace = "http://example.com/agency/schemas")
@XmlAccessorType(XmlAccessType.FIELD)
public class FindWithBalconyResponse {
    
    @XmlElement
    private FlatSoapDto flat;
    
    @XmlElement
    private ErrorResponseSoapDto error;
}