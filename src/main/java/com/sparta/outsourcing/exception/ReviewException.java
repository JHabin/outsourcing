package com.sparta.outsourcing.exception;

import lombok.Getter;

@Getter
public class ReviewException extends RuntimeException {
  private final ErrorCode errorCode;

  public ReviewException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public String getMessage() {
    return errorCode.getMessage();
  }

}
