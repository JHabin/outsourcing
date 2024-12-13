package com.sparta.outsourcing.dto.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DeactivateRequestDto {
    // final - 불변 객체
    @Pattern(regexp = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$", message = "이메일 형식이 일치하지 않습니다.")
    private final String email;

    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8글자, 영어, 숫자, 특수문자가 최소 1개씩 필요합니다.")
    private final String password;

    public DeactivateRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}