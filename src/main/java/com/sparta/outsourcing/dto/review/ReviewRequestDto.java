package com.sparta.outsourcing.dto.review;

import jakarta.validation.constraints.*;
import lombok.Getter;


@Getter

public class ReviewRequestDto {

  @NotNull(message = "주문 아이디를 입력하세요.")
  private final Long orderId;

  @NotNull(message = "별점을 입력해 주세요.")
  @Min(value = 1, message = "Rate는 최소 1이어야 합니다.")
  @Max(value = 5, message = "Rate는 최대 5이어야 합니다.")
  private final Integer rate;

  @NotBlank(message = "Content는 필수입니다.")
  @Size(min = 10, max = 200,message = "리뷰 내용은 10자 부터 200자 까지입니다.")
  private final String content;

  public ReviewRequestDto(Long orderId, Integer rate, String content) {
    this.orderId = orderId;
    this.rate = rate;
    this.content = content;
  }
}


