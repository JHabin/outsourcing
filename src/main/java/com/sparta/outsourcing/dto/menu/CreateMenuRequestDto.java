package com.sparta.outsourcing.dto.menu;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

/*
 * 클래스의 필드에 대해 Getter 메서드를 자동으로 생성해 줍니다.
 */
@Getter
/*
 * 메뉴 생성 요청을 처리하기 위한 Data Transfer Object(DTO) 클래스.
 * 클라이언트로부터 전달받은 메뉴 이름과 가격을 검증하고 저장합니다.
 */
public class CreateMenuRequestDto {

  /*
   * 메뉴 이름을 나타내는 필드.
   * - null 또는 빈 문자열을 허용하지 않음
   * - 유효하지 않은 경우, "메뉴를 입력해주세요"라는 메시지와 함께 예외가 발생
   */
  @NotEmpty(message = "메뉴를 입력해주세요 ")
  private final String name;

  /*
   * 메뉴 가격을 나타내는 필드.
   * - 최소값은 5000, 최대값은 25000
   * - 유효하지 않은 경우, 각각 "생성 최소 금액 : 5000" 또는 "생성 최대 금액 : 25000"이라는 메시지와 함께 예외가 발생
   */
  @Min(value = 5000, message = "생성 최소 금액 : 5000")
  @Max(value = 25000, message = "생성 최대 금액 : 25000")
  private final Long price;

  /**
   * CreateMenuRequestDto 객체를 생성합니다.
   *
   * @param name  메뉴의 이름
   * @param price 메뉴의 가격 (5000 이상, 25000 이하)
   */
  public CreateMenuRequestDto(String name, Long price) {
    this.name = name;
    this.price = price;
  }
}
