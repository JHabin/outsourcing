package com.sparta.outsourcing.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}