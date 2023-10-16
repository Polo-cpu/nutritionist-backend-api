package com.nutritionist.api.model.enums;

public enum MessageCodes implements IMessageCodes{
    OK(990),
    ERROR(991),
    CUSTOMER_NOT_CREATED(1001),
    CUSTOMER_NOT_FOUND(1002),
    CUSTOMER_NOT_DELETED(1003),
    NUTRITIONIST_NOT_CREATED(2001),
    NUTRITIONIST_NOT_FOUND(2002),
    NUTRITIONIST_NOT_DELETED(2003),
    NUTRITIONIST_NOT_AVAILABLE(2004),
    PRODUCT_NOT_CREATED(3001),
    PRODUCT_NOT_FOUND(3002),
    PRODUCT_NOT_DELETED(3003),
    USER_NOT_CREATED(4001),
    USER_NOT_FOUND(4002),
    USER_NOT_DELETED(4003);

    private final int value;
    MessageCodes(int value){
        this.value = value;
    }
    @Override
    public int getMessage() {
        return value;
    }
}
