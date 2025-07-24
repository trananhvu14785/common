package com.kane.common.exception;

import com.kane.common.response.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String message;
  private ErrorCode errorCode;
  private String domain = "auth";

  public AuthException(String message, ErrorCode errorCode, String domain) {
    this.message = message;
    this.errorCode = errorCode;
    this.domain = domain;
  }

  public AuthException(String message) {
    this(message, ErrorCode.UNAUTHORIZED, "auth");
  }
}
