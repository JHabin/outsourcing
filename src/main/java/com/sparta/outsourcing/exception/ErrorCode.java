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
    LESS_THAN_MINPRICE("최소주문금액 이상만 주문 가능합니다.", HttpStatus.BAD_REQUEST),
    NOT_IN_STORE("해당 메뉴는 지정된 가게에 속하지 않습니다. ", HttpStatus.BAD_REQUEST),
    NOT_FOUND_MENU("해당 메뉴를 조회할 수 없습니다.", HttpStatus.NOT_FOUND),
    ORDER_MUST_BELONG_TO_STORE("해당 가게의 주문에만 리뷰를 작성할 수 있습니다.",HttpStatus.BAD_REQUEST),

    // Store 예외 코드(추가 가능?)
    DEACTIVATED_STORE("이미 삭제된 가게입니다.", HttpStatus.NOT_FOUND),
    FORBIDDEN_STORE_CREATE("가게 생성 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_STORE_UPDATE("본인 가게만 수정 가능합니다.", HttpStatus.FORBIDDEN),
    FORBIDDEN_STORE_DELETE("본인 가게만 삭제 가능합니다.", HttpStatus.FORBIDDEN);

    private final String message;
    private final HttpStatus httpStatus;

}
