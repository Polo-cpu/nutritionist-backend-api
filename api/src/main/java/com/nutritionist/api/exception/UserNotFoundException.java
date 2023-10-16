package com.nutritionist.api.exception;

import com.nutritionist.api.model.enums.IMessageCodes;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.utils.MessageUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class UserNotFoundException extends RuntimeException{
    private final Language language;
    private final IMessageCodes messageCodes;
    public UserNotFoundException(Language language, IMessageCodes messageCodes){
        super(MessageUtils.getMessage(language,messageCodes));
        this.language = language;
        this.messageCodes = messageCodes;
        log.error("[UserNotCreatedException] -> message: {} developer message: {}",MessageUtils.getMessage(language,messageCodes),messageCodes);
    }

}
