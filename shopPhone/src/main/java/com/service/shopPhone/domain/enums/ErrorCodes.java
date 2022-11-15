package com.service.shopPhone.domain.enums;

public enum ErrorCodes {
    // Not Found group (404XXX)
    USER_NOT_EXIST(404001),
    BRAND_NOT_EXISTS(404002),
    PRODUCT_NOT_EXISTS(404003),
  
    // Bad Request group (400XXX)
    INVALID_FILE(400001),
    
  
    // Authentication group (401XXX)
    AUTHENTICATION_ERROR(401001),
  
    // Forbidden group (403XXX)
    VIEW_CLAIM_DETAILS_FORBIDDEN(403001),
    AGENT_PENDING_APPROVAL(403002),
  
    // Conflict group (409XXX)
    USER_EXISTED(409001),
    BRAND_NAME_EXISTED(409002),
  
    // Method Not Allowed group (405XXX)
    VALIDATE_MPIN_NOT_ALLOWED(405001),
  
    // Too many request group (429XXX)
    VALIDATE_NID_TOO_MANY_REQUEST(429005),
  
    // Internal Server Error group (500XXX)
    INVALID_STRING_EXCEPTION(500001),
    INTERNAL_SERVER_ERROR(500002);
    
  
    private final int code;
  
    ErrorCodes(int code) {
      this.code = code;
    }
  
    public int getCode() {
      return code;
    }
  
    public String getName() {
      return this.name();
    }
  
    @Override
    public String toString() {
      return this.name() + "(" + code + ")";
    }
  }
  