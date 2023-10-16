package com.nutritionist.api.exception;

import com.nutritionist.api.model.enums.IMessageCodes;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.utils.MessageUtils;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Getter
public class ProductNotFoundException extends RuntimeException{
    private final Language language;
    private final IMessageCodes messageCodes;
    public ProductNotFoundException(Language language, IMessageCodes messageCodes){
        super(MessageUtils.getMessage(language,messageCodes));
        this.language = language;
        this.messageCodes = messageCodes;
        log.error("[ProductNotCreatedException] -> message: {} developer message: {}",MessageUtils.getMessage(language,messageCodes),messageCodes);
    }
}
