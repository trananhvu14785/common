package com.kane.common.handler;

import com.kane.common.exception.*;
import com.kane.common.exception.IllegalArgumentException;
import com.kane.common.response.ErrorResponse;
import com.kane.common.response.GenericSuccessResponseData;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // có tác dụng xử lý các exception toàn cục
@ControllerAdvice
public class GlobalExceptionHandler {
  @ResponseStatus(value = HttpStatus.CONFLICT)
  @ExceptionHandler(exception = {UserAlreadyExitsException.class})
  public ErrorResponse handle(final UserAlreadyExitsException e) {
    return ErrorResponse.of(e.getErrorCode(), e.getDomain(), e.getMessage());
  }

  @ResponseStatus(value = HttpStatus.CONFLICT)
  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorResponse handle(final IllegalArgumentException exception) {
    return ErrorResponse.of(
        exception.getErrorCode(), exception.getDomain(), exception.getMessage());
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(DatabaseConstraintViolationException.class)
  public ErrorResponse handle(final DatabaseConstraintViolationException exception) {
    return ErrorResponse.of(
        exception.getErrorCode(), exception.getDomain(), exception.getMessage());
  }

  @ResponseStatus(value = HttpStatus.CONFLICT)
  @ExceptionHandler(PSQLException.class)
  public ErrorResponse handle(final PSQLException exception) {
    return ErrorResponse.of(
        exception.getErrorCode(), exception.getDomain(), exception.getMessage());
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoFoundException.class)
  public ErrorResponse handle(final NoFoundException exception) {
    return ErrorResponse.of(
        exception.getErrorCode(), exception.getDomain(), exception.getMessage());
  }

  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(AuthException.class)
  public ErrorResponse handle(final AuthException exception) {
    return ErrorResponse.of(
        exception.getErrorCode(), exception.getDomain(), exception.getMessage());
  }

  @ExceptionHandler(AccessDeniedCustomException.class)
  public ResponseEntity<GenericSuccessResponseData<Void>> handlerAccessDeniedCustomException(
      AccessDeniedCustomException exception) {
    GenericSuccessResponseData<Void> response =
        GenericSuccessResponseData.<Void>builder()
            .statusCode(HttpStatus.FORBIDDEN.value())
            .statusMessage(HttpStatus.FORBIDDEN.getReasonPhrase())
            .cufxMessage(exception.getMessage())
            .timestamp(new Date())
            .timeElapsedInMS(0)
            .data(null)
            .build();

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<GenericSuccessResponseData<Void>> handleRuntimeException(
      RuntimeException ex) {
    GenericSuccessResponseData<Void> response =
        GenericSuccessResponseData.<Void>builder()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .statusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .cufxMessage(ex.getMessage())
            .timestamp(new Date())
            .timeElapsedInMS(0)
            .data(null)
            .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
