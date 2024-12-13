package com.sparta.outsourcing.dto.store;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class UpdateStoreResponseDto {

    private final Long id;
    private final Long userId;
    private final String name;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final Integer minOrderPrice;

    public UpdateStoreResponseDto(Long id, Long userId, String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
    }
}
