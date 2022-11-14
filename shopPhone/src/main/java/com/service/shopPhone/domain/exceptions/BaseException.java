package com.service.shopPhone.domain.exceptions;

import java.io.Serializable;
import java.util.Map;

import com.service.shopPhone.domain.enums.ErrorCodes;

public class BaseException extends RuntimeException {

  private final ErrorCodes code;

  private final Map<String, Serializable> data;

  public BaseException() {
    super();

    this.code = null;
    this.data = null;
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);

    this.code = null;
    this.data = null;
  }

  public BaseException(String message) {
    super(message);

    this.code = null;
    this.data = null;
  }

  public BaseException(Throwable cause) {
    super(cause);

    this.code = null;
    this.data = null;
  }

  public BaseException(ErrorCodes code) {
    super();

    this.code = code;
    this.data = null;
  }

  public BaseException(Map<String, Serializable> data) {
    super();

    this.code = null;
    this.data = data;
  }

  public BaseException(String message, Throwable cause, ErrorCodes code) {
    super(message, cause);

    this.code = code;
    this.data = null;
  }

  public BaseException(String message, ErrorCodes code) {
    super(message);

    this.code = code;
    this.data = null;
  }

  public BaseException(Throwable cause, ErrorCodes code) {
    super(cause);

    this.code = code;
    this.data = null;
  }

  public BaseException(ErrorCodes code, Map<String, Serializable> data) {
    super();

    this.code = code;
    this.data = data;
  }

  public BaseException(String message, Throwable cause, Map<String, Serializable> data) {
    super(message, cause);

    this.code = null;
    this.data = data;
  }

  public BaseException(String message, Map<String, Serializable> data) {
    super(message);

    this.code = null;
    this.data = data;
  }

  public BaseException(Throwable cause, Map<String, Serializable> data) {
    super(cause);

    this.code = null;
    this.data = data;
  }

  public BaseException(
      String message, Throwable cause, ErrorCodes code, Map<String, Serializable> data) {
    super(message, cause);

    this.code = code;
    this.data = data;
  }

  public BaseException(String message, ErrorCodes code, Map<String, Serializable> data) {
    super(message);

    this.code = code;
    this.data = data;
  }

  public BaseException(Throwable cause, ErrorCodes code, Map<String, Serializable> data) {
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