package com.kane.common.handler;

import com.kane.common.exception.*;
import com.kane.common.exception.IllegalArgumentException;
import com.kane.common.response.ErrorResponse;
import com.kane.common.response.GenericSuccessResponseData;
import jakarta.validation.ConstraintViolation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // handles exceptions for all @RestController
@ControllerAdvice // can be used for both @Controller and @RestController
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
  public ResponseEntity<GenericSuccessResponseData> handlerAccessDeniedCustomException(
      AccessDeniedCustomException exception) {
    GenericSuccessResponseData response =
        GenericSuccessResponseData.builder()
            .statusCode(HttpStatus.FORBIDDEN.value())
            .statusMessage(HttpStatus.FORBIDDEN.getReasonPhrase())
            .cufxMessage(exception.getMessage())
            .timestamp(new Date())
            .timeElapsedInMS(0L)
            .data(null)
            .build();

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GenericSuccessResponseData> handleValidationException(
      MethodArgumentNotValidException ex) {
    long start = System.currentTimeMillis();

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              String fieldPath = error.getField();

              if (error.contains(ConstraintViolation.class)) {
                fieldPath =
                    ((ConstraintViolation<?>) error.unwrap(ConstraintViolation.class))
                        .getPropertyPath()
                        .toString();
              }

              errors.put(fieldPath, error.getDefaultMessage());
            });

    GenericSuccessResponseData response =
        GenericSuccessResponseData.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .statusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .cufxMessage("Validation failed")
            .timestamp(new Date())
            .timeElapsedInMS(System.currentTimeMillis() - start)
            .data(errors)
            .build();

    return ResponseEntity.badRequest().body(response);
  }
}
