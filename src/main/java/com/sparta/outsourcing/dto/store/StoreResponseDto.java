package com.sparta.outsourcing.dto.store;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class StoreResponseDto {

    private final Long id;
    private final String name;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final Integer minOrderPrice;

    public StoreResponseDto(Long id, String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        this.id = id;
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
    }

}
