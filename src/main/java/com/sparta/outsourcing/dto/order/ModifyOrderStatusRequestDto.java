package com.sparta.outsourcing.dto.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ModifyOrderStatusRequestDto {
    @NotBlank(message = "변경할 상태값은 필수 입력사항입니다.")
    private final String status;

    //필드가 하나일 때 @RequestBody 정상적으로 동작할 수 있도록 하는 어노테이션
    @JsonCreator
    public ModifyOrderStatusRequestDto(String status) {
        this.status = status;
    }
}
