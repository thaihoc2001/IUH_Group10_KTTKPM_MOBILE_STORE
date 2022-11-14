package com.service.shopPhone.domain.exceptions;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class BrandNotExistsException extends BaseException {

  public BrandNotExistsException(String message) {
    super(message, ErrorCodes.BRAND_NOT_EXISTS);
  }

  public BrandNotExistsException() {
    super(MessageConstant.BRAND_NOT_EXISTS, ErrorCodes.BRAND_NOT_EXISTS);
  }
}

