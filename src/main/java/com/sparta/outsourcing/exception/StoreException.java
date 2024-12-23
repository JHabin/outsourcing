package com.sparta.outsourcing.exception;

import lombok.Getter;

@Getter
public class StoreException extends RuntimeException{
    private final ErrorCode errorCode;

    public StoreException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

}
