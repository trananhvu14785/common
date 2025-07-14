package com.kane.common.exception;

import com.kane.common.response.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PSQLException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String message;
  private ErrorCode errorCode = ErrorCode.PSQL_ERROR;
  private String domain = "database";

  public PSQLException(String message) {
    this.message = message;
  }
}
