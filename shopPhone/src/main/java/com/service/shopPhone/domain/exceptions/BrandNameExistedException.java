package com.service.shopPhone.domain.exceptions;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.enums.ErrorCodes;

public class BrandNameExistedException extends BaseException{
    public BrandNameExistedException() {
        super(MessageConstant.BRAND_NAME_EXISTED, ErrorCodes.BRAND_NAME_EXISTED);
      }    
}
