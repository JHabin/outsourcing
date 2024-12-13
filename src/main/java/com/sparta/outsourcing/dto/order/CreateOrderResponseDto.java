package com.sparta.outsourcing.dto.order;

import com.sparta.outsourcing.common.Status;
import com.sparta.outsourcing.entity.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateOrderResponseDto {
    private final Long userId;
    private final Long menuId;
    private final Integer count;
    private final Integer totalPrice;
    private final String status;
    private final String rejectReason;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateOrderResponseDto (Order savedOrder) { //POST:주문 생성
        this.userId = savedOrder.getUser().getId();
        this.menuId = savedOrder.getMenu().getId();
        this.count = savedOrder.getCount();
        this.totalPrice = savedOrder.getTotalPrice();
        this.status = String.valueOf(savedOrder.getStatus());
        this.rejectReason = savedOrder.getRejectedReason();
        this.createdAt = savedOrder.getCreatedAt();
        this.modifiedAt = savedOrder.getModifiedAt();
    }

}
