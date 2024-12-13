package com.sparta.outsourcing.dto.menu;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CreateMenuRequestDto {
  @NotEmpty(message = "메뉴를 입력해주세요 ")
  private final String name;

  @Min(value = 5000, message = "생성 최소 금액 : 5000")
  @Max(value = 25000, message = "생성 최대 금액 : 25000")
  private final Long price;

  public CreateMenuRequestDto(String name, Long price) {
    this.name = name;
    this.price = price;
  }
}
