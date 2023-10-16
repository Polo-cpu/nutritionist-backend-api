package com.nutritionist.api.utils;

import com.nutritionist.api.model.enums.IMessageCodes;
import com.nutritionist.api.model.enums.Language;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@UtilityClass
public class MessageUtils {
    private static final String RESOURCE_BUNDLE_NAME = "language";
    private static final String SPECIAL_CHARACTER="__";
    public static String getMessage(Language language, IMessageCodes messageCodes){
        String messageKey = null;
        try{
            Locale locale = new Locale(language.name());
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
            messageKey = messageCodes.getClass().getSimpleName() + SPECIAL_CHARACTER + messageCodes;
            return resourceBundle.getString(messageKey);
        }catch (MissingResourceException missingResourceException){
            log.error("message not found for the key: {} ", messageKey);
            return null;
        }
    }
}
