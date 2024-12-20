package com.sparta.outsourcing.dto.menu;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/*
 * 클래스의 필드에 대해 Getter 메서드를 자동으로 생성
 */
@Getter
/*
 * 메뉴 수정 요청을 처리하기 위한 Data Transfer Object(DTO) 클래스
 * 클라이언트로부터 전달받은 메뉴 이름 데이터를 검증하고 저장
 */
public class ModifyMenuRequestDto {

  /**
   * 수정할 메뉴의 이름
   * - null, 빈 문자열, 공백만 포함된 문자열을 허용하지 않음
   * - 유효하지 않은 경우, "메뉴를 입력해주세요"라는 메시지와 함께 예외가 발생
   */
  @NotBlank(message = "메뉴를 입력해주세요 ")
  private final String name;

  /**
   * ModifyMenuRequestDto 객체를 생성
   *
   * @param name 수정할 메뉴의 이름
   *             필드가 하나인 RequestDto 경우 생성자의 JsonCreator 어노테이션을 추가해야한다.
   */
  @JsonCreator
  public ModifyMenuRequestDto(String name) {
    this.name = name;
  }

}
