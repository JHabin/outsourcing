package com.sparta.outsourcing.dto.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ModifyMenuRequestDto {
  @NotBlank(message = "메뉴를 입력해주세요 ")
  private final String name;

  public ModifyMenuRequestDto(String name) {
    this.name = name;
  }
}
