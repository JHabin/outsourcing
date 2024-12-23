package com.sparta.outsourcing.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // 예외를 처리할 수 있는 클래스라는 의미,  @Controller 또는 @RestController의 예외 다 처리 가능 횡단 관심사 - 여러 모듈에서 공통적으로 일어나는 것들 처리, responsebody 이용 가능
public class GlobalExceptionHandler {
    // UserException 예외가 발생했을 때 호출됨
    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(UserException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())  // HTTP 상태 코드
                .body(e.getErrorCode().getMessage());     // 예외 메시지
    }
    @ExceptionHandler(MenuException.class)
    public ResponseEntity<?> handleMenuException(MenuException e){
        return ResponseEntity
            .status(e.getErrorCode().getHttpStatus())  // HTTP 상태 코드
            .body(e.getErrorCode().getMessage());     // 예외 메시지
    }
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<?> handleOrderException(OrderException e){
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())  // HTTP 상태 코드
                .body(e.getErrorCode().getMessage());     // 예외 메시지
    }

    // StoreException 예외 발생 시 호출
    @ExceptionHandler(StoreException.class)
    public ResponseEntity<?> handleStoreException(StoreException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())  // HTTP 상태 코드
                .body(e.getErrorCode().getMessage());     // 예외 메시지
    }
}
