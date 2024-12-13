package com.sparta.outsourcing.dto.order;

import com.sparta.outsourcing.common.Status;
import com.sparta.outsourcing.entity.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ModifyOrderStatusResponseDto {
    private final Long userId;
    private final Long menuId;
    private final Integer count;
    private final Integer totalPrice;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public ModifyOrderStatusResponseDto(Long userId, Long menuId, Integer count, Integer totalPrice, Status status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.menuId = menuId;
        this.count = count;
        this.totalPrice = totalPrice;
        this.status = String.valueOf(status);
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ModifyOrderStatusResponseDto toDto(Long userId, Long menuId, Integer count, Integer totalPrice, Status status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ModifyOrderStatusResponseDto(
                userId,
                menuId,
                count,
                totalPrice,
                status,
                createdAt,
                modifiedAt
        );
    }
}
