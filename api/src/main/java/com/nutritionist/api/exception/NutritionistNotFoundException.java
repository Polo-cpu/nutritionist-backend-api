package com.nutritionist.api.exception;

import com.nutritionist.api.model.enums.IMessageCodes;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.utils.MessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class NutritionistNotFoundException extends RuntimeException {
    private final Language language;
    private final IMessageCodes messageCodes;
    public NutritionistNotFoundException(Language language, IMessageCodes messageCodes){
        super(MessageUtils.getMessage(language,messageCodes));
        this.language = language;
        this.messageCodes = messageCodes;
        log.error("[NutritionistNotCreatedException] -> message: {} developer message: {}",MessageUtils.getMessage(language,messageCodes),messageCodes);
    }
}
