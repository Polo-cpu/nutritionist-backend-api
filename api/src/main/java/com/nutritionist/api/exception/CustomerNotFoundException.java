package com.nutritionist.api.exception;

import com.nutritionist.api.model.enums.IMessageCodes;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.enums.MessageCodes;
import com.nutritionist.api.utils.MessageUtils;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CustomerNotFoundException extends RuntimeException{
    private final Language language;
    private final IMessageCodes messageCodes;
    public CustomerNotFoundException(Language language, IMessageCodes messageCodes){
        super(MessageUtils.getMessage(language,messageCodes));
        this.language = language;
        this.messageCodes = messageCodes;
        log.error("[CustomerNotCreatedException] -> message: {} developer message: {}",MessageUtils.getMessage(language,messageCodes),messageCodes);
    }

}
