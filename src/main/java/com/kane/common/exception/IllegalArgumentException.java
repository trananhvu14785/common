package com.kane.common.exception;

import com.kane.common.response.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IllegalArgumentException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String message;
  private ErrorCode errorCode = ErrorCode.ALREADY_EXIST;
  private String domain = "customer";

  public IllegalArgumentException(String message) {
    this.message = message;
  }
}
