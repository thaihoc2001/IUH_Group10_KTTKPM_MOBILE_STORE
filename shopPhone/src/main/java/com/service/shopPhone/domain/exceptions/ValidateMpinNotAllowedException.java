package com.service.shopPhone.domain.exceptions;

import java.io.Serializable;
import java.util.Map;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class ValidateMpinNotAllowedException extends BaseException {

    public ValidateMpinNotAllowedException() {
      super(MessageConstant.USER_X_CANT_VALIDATE_MPIN, ErrorCodes.VALIDATE_MPIN_NOT_ALLOWED);
    }
  
    public ValidateMpinNotAllowedException(Throwable cause) {
  
      super(MessageConstant.USER_X_CANT_VALIDATE_MPIN, cause, ErrorCodes.VALIDATE_MPIN_NOT_ALLOWED);
    }
  
    public ValidateMpinNotAllowedException(Map<String, Serializable> data) {
  
      super(
          MessageConstant.USER_X_CANT_VALIDATE_MPIN.formatted(data),
          ErrorCodes.VALIDATE_MPIN_NOT_ALLOWED,
          data);
    }
  
    public ValidateMpinNotAllowedException(Throwable cause, Map<String, Serializable> data) {
  
      super(
          MessageConstant.USER_X_CANT_VALIDATE_MPIN.formatted(data),
          cause,
          ErrorCodes.VALIDATE_MPIN_NOT_ALLOWED,
          data);
    }
  }
  
