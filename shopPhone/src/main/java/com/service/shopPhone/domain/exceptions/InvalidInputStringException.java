package com.service.shopPhone.domain.exceptions;

import java.io.Serializable;
import java.util.Map;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class InvalidInputStringException extends BaseException {
  public InvalidInputStringException(Map<String, Serializable> data) {

    super(
        MessageConstant.INVALID_STRING_EXCEPTION.formatted(data),
        ErrorCodes.INVALID_STRING_EXCEPTION,
        data);
  }
}
