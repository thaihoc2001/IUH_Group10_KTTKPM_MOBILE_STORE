package com.service.shopPhone.models;

import com.service.shopPhone.domain.enums.ErrorCodes;
import com.service.shopPhone.domain.exceptions.BaseException;
import com.service.shopPhone.domain.exceptions.PartnerBaseException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseErrorItem {

  private String reason;

  private String message;

  public static ResponseErrorItem from(Exception exception) {
    return ResponseErrorItem.builder()
        .reason(exception.getClass().getSimpleName())
        .message(exception.getMessage())
        .build();
  }

  public static ResponseErrorItem from(BaseException exception) {
    String reason = exception.getClass().getSimpleName();

    if (exception.getCode() != null) {
      ErrorCodes code = exception.getCode();

      reason = code.toString();
    }

    return ResponseErrorItem.builder().reason(reason).message(exception.getMessage()).build();
  }

  public static ResponseErrorItem fromPartner(PartnerBaseException exception) {
    String reason = exception.getClass().getSimpleName();

    if (exception.getCode() != null) {
      ErrorCodes code = exception.getCode();

      reason = String.valueOf(code.getCode());
    }

    return ResponseErrorItem.builder().reason(reason).message(exception.getMessage()).build();
  }
}

