package com.sparta.outsourcing.dto.menu;


import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CreateMenuResponseDto {

  private final Long id;
  private final Long storeId;
  private final String name;
  private final Integer price;
  private final LocalDateTime createdAt;

  public CreateMenuResponseDto(Long id, Long storeId, String name, Integer price, LocalDateTime createdAt) {
    this.id = id;
    this.storeId = storeId;
    this.name = name;
    this.price = price;
    this.createdAt = createdAt;
  }
}

