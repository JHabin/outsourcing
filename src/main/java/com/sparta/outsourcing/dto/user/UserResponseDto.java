package com.sparta.outsourcing.dto.user;

import com.sparta.outsourcing.common.Role;
import com.sparta.outsourcing.entity.User;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {
    private final String email;

    private final String password;

    private final String nickname;

    private final String address;

    private final String phone;

    private final Role role;

    private final LocalDateTime createdAt;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }

}