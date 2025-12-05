package com.foliageh.controller.soapDto;

import javax.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "error_response", propOrder = {"timestamp", "message", "errors"})
public class ErrorResponseSoapDto {
    
    @XmlElement(required = true)
    private String timestamp;
    
    @XmlElement(required = true)
    private String message;
    
    @XmlElementWrapper(name = "errors")
    @XmlElement(name = "error")
    private List<String> errors;
}