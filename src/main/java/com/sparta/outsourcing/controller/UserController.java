package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.entity.User;
import com.sparta.outsourcing.dto.user.LoginRequestDto;
import com.sparta.outsourcing.dto.user.SignUpRequestDto;
import com.sparta.outsourcing.dto.user.SignUpResponseDto;
import com.sparta.outsourcing.exception.AlreadyLoggedInException;
import com.sparta.outsourcing.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sparta.outsourcing.exception.ErrorCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        //요청 데이터 dto 로부터 파라미터 값을 받아 회원 가입하는 메소드 구현
        SignUpResponseDto responseDto = userService.signUp(signUpRequestDto);
        //201 Created 반환하고 SignUpResponseDto 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
