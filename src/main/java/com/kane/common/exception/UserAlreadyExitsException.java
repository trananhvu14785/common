package com.kane.common.exception;

import com.kane.common.response.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAlreadyExitsException extends RuntimeException {
  // serialVersionUID là một số nguyên duy nhất được sử dụng để xác định phiên bản của lớp khi thực
  // hiện quá trình
  // tuần tự hóa (serialization) và giải tuần tự hóa (deserialization).
  private static final long serialVersionUID = 1L;

  private final ErrorCode errorCode = ErrorCode.ALREADY_EXIST;
  private final String domain = "user";
  private String message;

  public UserAlreadyExitsException(String message) {
    this.message = message;
  }
}
