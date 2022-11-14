package com.service.shopPhone.domain.exceptions;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class InternalServerErrorException extends BaseException{
    public InternalServerErrorException() {
        super(MessageConstant.INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR);
      }
}
