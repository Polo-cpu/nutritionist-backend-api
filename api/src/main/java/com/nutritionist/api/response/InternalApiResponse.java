package com.nutritionist.api.response;

import lombok.Builder;
import lombok.Data;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class InternalApiResponse<T> {
    private MessageResponse messageResponse;
    private T payload;
    private boolean hasError;
    private List<String> errorMessages;
    private HttpStatus httpStatus;

}
