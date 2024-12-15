package com.sparta.outsourcing.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATED_EMAIL("중복된 아이디입니다.", HttpStatus.CONFLICT),
    ALEADY_LOGGED_IN("이미 로그인되어 있습니다.", HttpStatus.BAD_REQUEST),
    DEACTIVATED_USER("이미 탈퇴한 회원입니다.", HttpStatus.FORBIDDEN),
    PASSWORD_INCORRECT("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
    private final String message;
    private final HttpStatus httpStatus;

}
