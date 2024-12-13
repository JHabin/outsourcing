package com.sparta.outsourcing.dto.user;

import com.sparta.outsourcing.common.Role;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private final String email;

    private final String nickname;

    private final String address;

    private final String phone;

    private final Role role;

    private final LocalDateTime createdAt;

    public SignUpResponseDto(String email, String nickname, String address, String phone, Role role, LocalDateTime createdAt) {
        this.email = email;
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
        this.role = role;
        this.createdAt = createdAt;
    }
}