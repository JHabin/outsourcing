package com.sparta.outsourcing.dto.menu;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
/*
 * 메뉴 수정 응답을 처리하기 위한 Data Transfer Object(DTO) 클래스.
 * 메뉴 수정 요청에 대한 결과를 클라이언트에게 반환하기 위해 사용
 */
public class ModifyMenuResponseDto {

  /*
   * 고유 식별자.
   */
  private final Long id;

  /*
   * 메뉴가 속한 가게의 고유 식별자.
   */
  private final Long storeId;

  /*
   * 수정된 메뉴의 이름.
   */
  private final String name;

  /*
   * 메뉴의 가격.
   */
  private final Integer price;

  /*
   * 메뉴가 생성된 날짜와 시간.
   */
  private final LocalDateTime createdAt;

  /**
   * ModifyMenuResponseDto 객체를 생성
   *
   * @param id        수정된 메뉴의 고유 식별자
   * @param storeId   메뉴가 속한 가게의 고유 식별자
   * @param name      수정된 메뉴의 이름
   * @param price     수정된 메뉴의 가격
   * @param createdAt 메뉴가 생성된 날짜와 시간
   */
  public ModifyMenuResponseDto(Long id, Long storeId, String name, Integer price, LocalDateTime createdAt) {
    this.id = id;
    this.storeId = storeId;
    this.name = name;
    this.price = price;
    this.createdAt = createdAt;
  }
}
