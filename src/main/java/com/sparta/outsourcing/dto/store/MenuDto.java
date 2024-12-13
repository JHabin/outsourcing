package com.sparta.outsourcing.dto.store;

import lombok.Getter;

@Getter
public class MenuDto {

    private final String name;
    private final Integer price;

    public MenuDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
