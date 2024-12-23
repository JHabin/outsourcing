package com.sparta.outsourcing.exception;

import lombok.Getter;

@Getter
public class MenuException extends RuntimeException {
  private final ErrorCode errorCode;

  public MenuException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public String getMessage() {
    return errorCode.getMessage();
  }
}
