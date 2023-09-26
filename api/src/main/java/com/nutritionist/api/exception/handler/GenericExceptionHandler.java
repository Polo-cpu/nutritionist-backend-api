package com.nutritionist.api.exception.handler;

import com.nutritionist.api.exception.CustomerNotFoundException;
import com.nutritionist.api.exception.ProductException;
import com.nutritionist.api.exception.UserNotFoundException;
import com.nutritionist.api.model.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("error_message",exception.getMessage());
        errorMap.put("error_cause",exception.getCause().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Map<String,String>> handleProductException(ProductException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("error_message",exception.getMessage());
        errorMap.put("error_cause",exception.getCause().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCustomerNotFoundException(CustomerNotFoundException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("error_message",exception.getMessage());
        errorMap.put("error_cause",exception.getCause().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }

}
