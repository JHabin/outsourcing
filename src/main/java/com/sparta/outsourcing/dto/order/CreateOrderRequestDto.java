package com.sparta.outsourcing.dto.order;

import lombok.Getter;

@Getter
public class CreateOrderRequestDto {
    private final Long menuId;
    private final Integer count; //min, max 추가하기

    public CreateOrderRequestDto(Long menuId, Integer count) {
        this.menuId = menuId;
        this.count = count;
    }
}
