package com.sparta.outsourcing.dto.user;

import com.sparta.outsourcing.common.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;


@Getter
public class SignUpRequestDto {
    @NotBlank(message = "이메일은 필수값입니다.")
    @Pattern(regexp = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$", message = "이메일 형식이 일치하지 않습니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8글자이어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 영어, 숫자, 특수문자가 최소 1개씩 필요합니다.")
    private final String password;

    @NotBlank(message = "닉네임은 필수값입니다.")
    private final String nickname;

    @NotBlank(message = "주소는 필수값입니다.")
    private final String address;
    // NotBlank - null, 공백, 빈칸 허용 안 함
    // NotEmpty - null, 공백
    // NotNull - null
    @NotBlank(message = "전화번호는 필수값입니다.")
    @Pattern(regexp = "^01[016789]-\\d{3,4}-\\d{4}$", message = "전화번호 입력은 -를 포함해야 합니다.")
    private final String phone;

    @NotNull(message = "권한은 필수값입니다.")
    private final String role;

    public SignUpRequestDto(String email, String password, String nickname, String address, String phone, String role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }
}
