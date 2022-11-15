package com.service.shopPhone.domain.exceptions;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class ProductNotExistsException extends BaseException{
    public ProductNotExistsException() {
        super(MessageConstant.PRODUCT_NOT_EXISTS, ErrorCodes.PRODUCT_NOT_EXISTS);
      }
}
