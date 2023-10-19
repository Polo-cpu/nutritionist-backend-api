package com.nutritionist.api.exception;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
public class ProductException extends RuntimeException{
    private String details;
    public ProductException(String entityName, String reason){
        super("This" + entityName + "not found : " + reason);
    }

}
