package com.service.shopPhone.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.service.shopPhone.domain.constants.MessageConstant;
import com.service.shopPhone.domain.exceptions.BaseException;
import com.service.shopPhone.domain.exceptions.BrandNameExistedException;
import com.service.shopPhone.domain.exceptions.BrandNotExistsException;
import com.service.shopPhone.domain.exceptions.InternalServerErrorException;
import com.service.shopPhone.domain.exceptions.InvalidFileException;
import com.service.shopPhone.domain.exceptions.InvalidInputStringException;
import com.service.shopPhone.domain.exceptions.OrderNotExistsException;
import com.service.shopPhone.domain.exceptions.ProductNotExistsException;
import com.service.shopPhone.domain.exceptions.ValidateMpinNotAllowedException;
import com.service.shopPhone.domain.exceptions.ValidateNidTooManyException;
import com.service.shopPhone.models.ErrorResponse;
import com.service.shopPhone.models.ResponseError;
import com.service.shopPhone.models.ResponseErrorItem;
import com.service.shopPhone.utility.RequestCorrelation;


@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

  

  public GlobalControllerExceptionHandler() {
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();

    List<ResponseErrorItem> fieldErrors =
        bindingResult.getFieldErrors().stream()
            .map(
                fieldError ->
                    ResponseErrorItem.builder()
                        .reason(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
            .toList();

    List<ResponseErrorItem> objectErrors =
        bindingResult.getGlobalErrors().stream()
            .map(
                objectError ->
                    ResponseErrorItem.builder()
                        .reason(objectError.getObjectName())
                        .message(objectError.getDefaultMessage())
                        .build())
            .toList();

    List<ResponseErrorItem> errors =
        Stream.concat(fieldErrors.stream(), objectErrors.stream()).toList();
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(MessageConstant.VALIDATION_FAILED)
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(
      ConstraintViolationException exception) {
    Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

    List<ResponseErrorItem> errors =
        constraintViolations.stream()
            .map(
                constraintViolation -> {
                  Path propertyPath = constraintViolation.getPropertyPath();
                  Iterator<Node> nodeIterator = propertyPath.iterator();
                  Node lastNode = null;

                  while (nodeIterator.hasNext()) {
                    lastNode = nodeIterator.next();
                  }

                  return ResponseErrorItem.builder()
                      .reason(lastNode != null ? lastNode.getName() : propertyPath.toString())
                      .message(constraintViolation.getMessage())
                      .build();
                })
            .toList();
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(MessageConstant.VALIDATION_FAILED)
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
    BrandNotExistsException.class,
    ProductNotExistsException.class,
    OrderNotExistsException.class
  })
  public ResponseEntity<ErrorResponse> handleNotFoundExceptions(BaseException exception) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
    InvalidFileException.class,
  })
  public ResponseEntity<ErrorResponse> handleBadRequestExceptions(BaseException exception) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
    BrandNameExistedException.class
  })
  public ResponseEntity<ErrorResponse> handleConflictExceptions(BaseException exception) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.CONFLICT.value())
                    .message(HttpStatus.CONFLICT.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler({
    ValidateMpinNotAllowedException.class,
  })
  public ResponseEntity<ErrorResponse> handleMethodNotAllowedExceptions(BaseException exception) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                    .message(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler({
    InvalidInputStringException.class,
    InternalServerErrorException.class
  })
  public ResponseEntity<ErrorResponse> handleInternalServerErrorExceptions(
      BaseException exception) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(
      AuthenticationException exception, HttpServletResponse response) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({})
  public ResponseEntity<ErrorResponse> handleForbiddenExceptions(BaseException exception) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.FORBIDDEN.value())
                    .message(HttpStatus.FORBIDDEN.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler()
  public ResponseEntity<ErrorResponse> handleGenericExceptions(Exception exception) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({
    ValidateNidTooManyException.class
  })
  public ResponseEntity<ErrorResponse> handleTooManyRequestExceptions(BaseException exception) {
    ResponseErrorItem responseErrorItem = ResponseErrorItem.from(exception);
    List<ResponseErrorItem> errors = List.of(responseErrorItem);

    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .id(RequestCorrelation.getRequestId())
            .error(
                ResponseError.builder()
                    .code(HttpStatus.TOO_MANY_REQUESTS.value())
                    .message(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase())
                    .errors(errors)
                    .build())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.TOO_MANY_REQUESTS);
  }
}