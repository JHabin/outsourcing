package com.sparta.outsourcing.exception;

import lombok.Getter;

@Getter
public class WrongAccessException extends OrderException {
    public WrongAccessException (final ErrorCode errorcode) {
        super(errorcode);
    }
}
