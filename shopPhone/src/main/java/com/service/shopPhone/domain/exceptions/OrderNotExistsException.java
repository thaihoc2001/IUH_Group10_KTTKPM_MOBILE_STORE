package com.service.shopPhone.domain.exceptions;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class OrderNotExistsException extends BaseException{
    public OrderNotExistsException() {
        super(MessageConstant.ORDER_NOT_EXISTS, ErrorCodes.ORDER_NOT_EXISTS);
    }    
}
