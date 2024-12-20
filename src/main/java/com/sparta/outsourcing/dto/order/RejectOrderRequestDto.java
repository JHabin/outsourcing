package com.sparta.outsourcing.dto.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RejectOrderRequestDto {
    @NotBlank(message = "거절사유는 필수 입력 사항입니다.")
    private final String rejectReason;

    @JsonCreator
    public RejectOrderRequestDto(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
