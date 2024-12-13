package com.sparta.outsourcing.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RejectOrderRequestDto {
    private final Long menuId;
    private final Integer count;
    private final String status;

    @NotBlank(message = "거절사유는 필수 입력 사항입니다.")
    private final String rejectReason;

    public RejectOrderRequestDto(Long menuId, Integer count, String status, String rejectReason) {
        this.menuId = menuId;
        this.count = count;
        this.status = status;
        this.rejectReason = rejectReason;
    }
}
