package com.sparta.outsourcing.dto.user;

import com.sparta.outsourcing.common.Role;
import com.sparta.outsourcing.entity.User;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpResponseDto {
    private final String email;

    private final String password;

    private final String nickname;

    private final String address;

    private final String phone;

    private final Role role;

    private final LocalDateTime createdAt;

    public static SignUpResponseDto toDto(User user) {
        return new SignUpResponseDto(
                user.getEmail(),
                user.getPassword(),
                user.getNickname(),
                user.getAddress(),
                user.getPhone(),
                user.getRole(),
                user.getCreatedAt()
        );


    }


}