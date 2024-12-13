package com.sparta.outsourcing.dto.store;

import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
public class StoreMenuResponseDto {

    private final Long id;
    private final String name;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final Integer minOrderPrice;
    private final List<MenuDto> menus;

    public StoreMenuResponseDto(Long id, String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice, List<MenuDto> menus) {
        this.id = id;
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
        this.menus = menus;
    }
}
