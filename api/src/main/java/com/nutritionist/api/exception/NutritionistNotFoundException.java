package com.nutritionist.api.exception;

public class NutritionistNotFoundException extends RuntimeException {
    private String details;
    public NutritionistNotFoundException(String entityName, String reason){
        super("This" + entityName + "not found : " + reason);
    }
}
