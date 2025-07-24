package com.kane.common.exception;

import com.kane.common.response.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String message;
  private ErrorCode errorCode = ErrorCode.NOT_FOUND;
  private String domain = "customer";

  public NoFoundException(String message) {
    this.message = message;
  }
}
