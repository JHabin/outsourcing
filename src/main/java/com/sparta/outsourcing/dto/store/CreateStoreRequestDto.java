package com.sparta.outsourcing.dto.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class CreateStoreRequestDto {

    // @NotBlank는 문자열(String)에 적합한 어노테이션.
    // LocalTime과 Integer와 같은 비문자열 타입은 @NotNull을 사용해야 한다.
    @NotBlank(message = "가게명은 필수 입력 항목입니다.")
    private final String name;

    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "오픈 시간은 필수 입력 항목입니다.")
    private final LocalTime openTime;

    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "마감 시간은 필수 입력 항목입니다.")
    private final LocalTime closeTime;

    // @Min -> 최소값 검증. null은 허용하므로 @NotNull을 같이 사용해야 한다.
    @Min(value = 1, message = "최소 주문 금액은 1원 이상이어야 합니다.")
    @NotNull(message = "최소 주문 금액은 필수 입력 항목입니다.")
    private final Integer minOrderPrice;

    public CreateStoreRequestDto(String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
    }

}
