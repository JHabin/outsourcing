package com.sparta.outsourcing.common;

import lombok.Getter;

@Getter
public class Authentication {

    private final String email;
    private final Role role;

    public Authentication(String email, Role role) {
        this.email = email;
        this.role = role;
    }
}
