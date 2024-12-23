package com.sparta.outsourcing.dto.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class UpdateStoreRequestDto {

    @NotBlank(message = "가게명은 필수 입력 항목입니다.")
    private final String name;

    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "오픈 시간은 필수 입력 항목입니다.")
    private final LocalTime openTime;

    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "마감 시간은 필수 입력 항목입니다.")
    private final LocalTime closeTime;

    @Min(value = 1, message = "최소 주문 금액은 1원 이상이어야 합니다.")
    @NotNull(message = "최소 주문 금액은 필수 입력 항목입니다.")
    private final Integer minOrderPrice;

    public UpdateStoreRequestDto(String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
    }

}
