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
    private final Status status;
    private final String rejectReason;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateOrderResponseDto(Order savedOrder) { //POST:주문 생성
        this.userId = savedOrder.getUser().getId();
        this.menuId = savedOrder.getMenu().getId();
        this.count = savedOrder.getCount();
        this.totalPrice = savedOrder.getTotalPrice();
        this.status = savedOrder.getStatus();
        this.rejectReason = savedOrder.getRejectedReason();
        this.createdAt = savedOrder.getCreatedAt();
        this.modifiedAt = savedOrder.getModifiedAt();
    }

    public CreateOrderResponseDto(Long userId, Long menuId, Integer count, Integer totalPrice, Status status, String rejectedReason, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.menuId = menuId;
        this.count = count;
        this.totalPrice = totalPrice;
        this.status = status;
        this.rejectReason = rejectedReason;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
