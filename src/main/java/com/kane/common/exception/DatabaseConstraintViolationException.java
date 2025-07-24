package com.kane.common.exception;

import com.kane.common.response.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseConstraintViolationException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String message;
  private ErrorCode errorCode = ErrorCode.INVALID_ARGUMENT;
  private String domain = "database";

  public DatabaseConstraintViolationException(String message) {
    this.message = message;
  }
}
