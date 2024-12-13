package com.sparta.outsourcing.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
  private final Long id;
  private final Integer rate;
  private final String content;
  private final LocalDateTime createdAt;
  private final Long orderId;
  private final Long userId;

  public ReviewResponseDto(Long id, Integer rate, String content, LocalDateTime createdAt, Long orderId, Long userId) {
    this.id = id;
    this.rate = rate;
    this.content = content;
    this.createdAt = createdAt;
    this.orderId = orderId;
    this.userId = userId;
  }
}

