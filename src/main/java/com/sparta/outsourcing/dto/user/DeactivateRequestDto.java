package com.sparta.outsourcing.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DeactivateRequestDto {

    @Min(value = 8)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8글자, 영어, 숫자, 특수문자가 최소 1개씩 필요합니다.")
    private final String password;

    @JsonCreator
    public DeactivateRequestDto(String password) {

        this.password = password;
    }
}
