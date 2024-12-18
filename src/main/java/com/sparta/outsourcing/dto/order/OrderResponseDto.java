package com.sparta.outsourcing.dto.order;

import com.sparta.outsourcing.common.Status;
import com.sparta.outsourcing.entity.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponseDto {
    private final Long userId;
    private final Long menuId;
    private final Integer count;
    private final Integer totalPrice;
    private final String status;
    private final String rejectReason;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public OrderResponseDto(Long userId, Long menuId, Integer count, Integer totalPrice, Status status, String rejectedReason, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.menuId = menuId;
        this.count = count;
        this.totalPrice = totalPrice;
        this.status = String.valueOf(status);
        this.rejectReason = rejectedReason;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getUser().getId(),
                order.getMenu().getId(),
                order.getCount(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getRejectedReason(),
                order.getCreatedAt(),
                order.getModifiedAt()
        );
    }
}
