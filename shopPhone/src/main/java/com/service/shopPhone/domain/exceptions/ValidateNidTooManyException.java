package com.service.shopPhone.domain.exceptions;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class ValidateNidTooManyException extends BaseException {

  public ValidateNidTooManyException() {
    super(
        MessageConstant.VALIDATE_NID_TOO_MANY_REQUEST_ERROR,
        ErrorCodes.VALIDATE_NID_TOO_MANY_REQUEST);
  }

  public ValidateNidTooManyException(Throwable cause) {

    super(
        MessageConstant.VALIDATE_NID_TOO_MANY_REQUEST_ERROR,
        cause,
        ErrorCodes.VALIDATE_NID_TOO_MANY_REQUEST);
  }
}

