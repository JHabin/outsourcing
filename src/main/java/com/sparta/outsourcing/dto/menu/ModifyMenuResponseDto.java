package com.sparta.outsourcing.dto.menu;

import java.time.LocalDateTime;

public class ModifyMenuResponseDto {

  private final Long id;
  private final Long storeId;
  private final String name;
  private final Integer price;
  private final LocalDateTime createdAt;

  public ModifyMenuResponseDto(Long id, Long storeId, String name, Integer price, LocalDateTime createdAt) {
    this.id = id;
    this.storeId = storeId;
    this.name = name;
    this.price = price;
    this.createdAt = createdAt;
  }
}
