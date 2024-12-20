package com.sparta.outsourcing.dto.order;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class CreateOrderRequestDto {
    private final Long menuId;

    @Min(value = 1, message = "주문 최소 수량은 1건 입니다.")
    private final Integer count;

    public CreateOrderRequestDto(Long menuId, Integer count) {
        this.menuId = menuId;
        this.count = count;
    }
}
