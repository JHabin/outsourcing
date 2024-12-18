package com.sparta.outsourcing.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeactivatedException extends RuntimeException {

  private final ErrorCode errorCode;

  @Override
  public String getMessage() {
    return errorCode.getMessage();
  }
}