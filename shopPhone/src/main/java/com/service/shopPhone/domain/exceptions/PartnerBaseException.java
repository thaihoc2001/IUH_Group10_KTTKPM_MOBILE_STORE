package com.service.shopPhone.domain.exceptions;

import java.io.Serializable;
import java.util.Map;

import com.service.shopPhone.domain.enums.ErrorCodes;

public class PartnerBaseException extends RuntimeException {

  private final ErrorCodes code;

  private final Map<String, Serializable> data;

  public PartnerBaseException() {
    super();

    this.code = null;
    this.data = null;
  }

  public PartnerBaseException(String message, Throwable cause) {
    super(message, cause);

    this.code = null;
    this.data = null;
  }

  public PartnerBaseException(String message) {
    super(message);

    this.code = null;
    this.data = null;
  }

  public PartnerBaseException(Throwable cause) {
    super(cause);

    this.code = null;
    this.data = null;
  }

  public PartnerBaseException(ErrorCodes code) {
    super();

    this.code = code;
    this.data = null;
  }

  public PartnerBaseException(Map<String, Serializable> data) {
    super();

    this.code = null;
    this.data = data;
  }

  public PartnerBaseException(String message, Throwable cause, ErrorCodes code) {
    super(message, cause);

    this.code = code;
    this.data = null;
  }

  public PartnerBaseException(String message, ErrorCodes code) {
    super(message);

    this.code = code;
    this.data = null;
  }

  public PartnerBaseException(Throwable cause, ErrorCodes code) {
    super(cause);

    this.code = code;
    this.data = null;
  }

  public PartnerBaseException(ErrorCodes code, Map<String, Serializable> data) {
    super();

    this.code = code;
    this.data = data;
  }

  public PartnerBaseException(String message, Throwable cause, Map<String, Serializable> data) {
    super(message, cause);

    this.code = null;
    this.data = data;
  }

  public PartnerBaseException(String message, Map<String, Serializable> data) {
    super(message);

    this.code = null;
    this.data = data;
  }

  public PartnerBaseException(Throwable cause, Map<String, Serializable> data) {
    super(cause);

    this.code = null;
    this.data = data;
  }

  public PartnerBaseException(
      String message, Throwable cause, ErrorCodes code, Map<String, Serializable> data) {
    super(message, cause);

    this.code = code;
    this.data = data;
  }

  public PartnerBaseException(String message, ErrorCodes code, Map<String, Serializable> data) {
    super(message);

    this.code = code;
    this.data = data;
  }

  public PartnerBaseException(Throwable cause, ErrorCodes code, Map<String, Serializable> data) {
    super(cause);

    this.code = code;
    this.data = data;
  }

  public ErrorCodes getCode() {
    return this.code;
  }

  public Map<String, Serializable> getData() {
    return this.data;
  }
}
