package com.nutritionist.api.exception.handler;

import com.nutritionist.api.exception.*;
import com.nutritionist.api.model.enums.MessageCodes;
import com.nutritionist.api.response.InternalApiResponse;
import com.nutritionist.api.response.MessageResponse;
import com.nutritionist.api.utils.MessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Collections;

@RestControllerAdvice
public class GenericExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public InternalApiResponse<String> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(userNotFoundException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(userNotFoundException.getLanguage(),userNotFoundException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(userNotFoundException.getMessage()))
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public InternalApiResponse<String> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(productNotFoundException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(productNotFoundException.getLanguage(),productNotFoundException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(productNotFoundException.getMessage()))
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerNotFoundException.class)
    public InternalApiResponse<String> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(customerNotFoundException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(customerNotFoundException.getLanguage(),customerNotFoundException.getMessageCodes()))
                .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(customerNotFoundException.getMessage()))
                .build();
    }
    @ExceptionHandler(NutritionistNotFoundException.class)
    public InternalApiResponse<String> handleNutritionistNotFoundException(NutritionistNotFoundException nutritionistNotFoundException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(nutritionistNotFoundException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(nutritionistNotFoundException.getLanguage(),nutritionistNotFoundException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(nutritionistNotFoundException.getMessage()))
                .build();
    }
    @ExceptionHandler(IncorrectPasswordException.class)
    public InternalApiResponse<String> handleIncorrectPasswordException(IncorrectPasswordException incorrectPasswordException){
        return  InternalApiResponse.<String>builder()
                .messageResponse(MessageResponse.builder().title(MessageUtils.getMessage(incorrectPasswordException.getLanguage(), MessageCodes.ERROR))
                        .description(MessageUtils.getMessage(incorrectPasswordException.getLanguage(),incorrectPasswordException.getMessageCodes()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(incorrectPasswordException.getMessage()))
                .build();
    }
}
