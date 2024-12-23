package com.sparta.outsourcing.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    DUPLICATED_EMAIL("중복된 아이디입니다.", HttpStatus.CONFLICT),
    ALREADY_LOGGED_IN("이미 로그인되어 있습니다.", HttpStatus.BAD_REQUEST),
    DEACTIVATED_USER("이미 탈퇴한 회원입니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND("해당 아이디의 회원을 조회할 수 없습니다.", HttpStatus.NOT_FOUND),
    UNAUTHORIZED("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED),
    SESSION_INVALID("세션이 없습니다.", HttpStatus.UNAUTHORIZED),
    ROLE_MISMATCH("권한이 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    AUTH_MISMATCH("본인이 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    PASSWORD_INCORRECT("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_OPEN_STORE("영업 시간이 아닙니다.",HttpStatus.BAD_REQUEST),
    LESS_THAN_MINPRICE("최소주문금액 이상만 주문 가능합니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

}
