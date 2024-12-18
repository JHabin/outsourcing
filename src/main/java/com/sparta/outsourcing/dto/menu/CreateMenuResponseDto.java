package com.sparta.outsourcing.dto.menu;


import lombok.Getter;

import java.time.LocalDateTime;

/*
 * 클래스의 필드에 대해 Getter 메서드를 자동으로 생성
 */
@Getter
/*
 * 메뉴 생성 응답을 처리하기 위한 Data Transfer Object(DTO) 클래스.
 * 메뉴 생성 요청에 대한 결과를 클라이언트에게 반환하기 위해 사용
 */
public class CreateMenuResponseDto {

  /*
   * 생성된 메뉴의 고유 식별자.
   */
  private final Long id;

  /*
   * 메뉴가 속한 가게의 고유 식별자.
   */
  private final Long storeId;

  /*
   * 생성된 메뉴의 이름.
   */
  private final String name;

  /*
   * 생성된 메뉴의 가격.
   */
  private final Integer price;

  /*
   * 메뉴가 생성된 날짜와 시간.
   */
  private final LocalDateTime createdAt;

  /**
   * CreateMenuResponseDto 객체를 생성
   *
   * @param id        생성된 메뉴의 고유 식별자
   * @param storeId   메뉴가 속한 가게의 고유 식별자
   * @param name      생성된 메뉴의 이름
   * @param price     생성된 메뉴의 가격
   * @param createdAt 메뉴가 생성된 날짜와 시간
   */
  public CreateMenuResponseDto(Long id, Long storeId, String name, Integer price, LocalDateTime createdAt) {
    this.id = id;
    this.storeId = storeId;
    this.name = name;
    this.price = price;
    this.createdAt = createdAt;
  }
}

