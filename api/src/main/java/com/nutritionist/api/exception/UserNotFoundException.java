package com.nutritionist.api.exception;

import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
public class UserNotFoundException extends RuntimeException{
    private String details;
    public UserNotFoundException(String entityName, String reason){
        super("This" + entityName + "not found : " + reason);
    }

}
