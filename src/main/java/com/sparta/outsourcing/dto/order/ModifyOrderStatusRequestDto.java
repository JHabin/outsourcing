package com.sparta.outsourcing.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ModifyOrderStatusRequestDto {
    private final Long menuId;
    private final Integer count;

    @NotBlank(message = "변경할 상태값은 필수 입력사항입니다.")
    private final String status;

    public ModifyOrderStatusRequestDto(Long menuId, Integer count, String status, String rejectReason) {
        this.menuId = menuId;
        this.count = count;
        this.status = status;
    }
}
