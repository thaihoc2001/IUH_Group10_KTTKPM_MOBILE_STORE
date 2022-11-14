package com.service.shopPhone.domain.exceptions;

import java.util.Map;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class InvalidFileException extends BaseException {

    public InvalidFileException() {
      super(MessageConstant.INVALID_FILE_ERROR, ErrorCodes.INVALID_FILE);
    }
  
    public InvalidFileException(Throwable cause) {
  
      super(MessageConstant.INVALID_FILE_ERROR, cause, ErrorCodes.INVALID_FILE);
    }
  }
  