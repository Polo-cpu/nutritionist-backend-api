package com.nutritionist.api.exception;

import lombok.Data;

@Data
public class CustomerNotFoundException extends RuntimeException{
    private String details;
    public CustomerNotFoundException(String entityName, String reason){
        super("This" + entityName + "not found : " + reason);
    }

}
