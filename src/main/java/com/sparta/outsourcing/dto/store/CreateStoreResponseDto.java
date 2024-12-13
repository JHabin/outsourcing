package com.sparta.outsourcing.dto.store;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class CreateStoreResponseDto {

    private final Long id;
    private final Long userId;
    private final String name;
    private final LocalTime openTime;
    private final LocalTime closeTime;
    private final Integer minOrderPrice; // minOrderPrice
    private final LocalDateTime createdAt;

    public CreateStoreResponseDto(Long id, Long userId, String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
        this.createdAt = createdAt;
    }
}
