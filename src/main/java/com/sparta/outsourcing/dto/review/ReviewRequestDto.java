package com.sparta.outsourcing.dto.review;

import jakarta.validation.constraints.*;
import lombok.Getter;

/*
 * 클래스의 필드에 대해 Getter 메서드를 자동으로 생성
 */
@Getter

/*
 * 리뷰 생성 요청을 처리하기 위한 Data Transfer Object(DTO) 클래스
 * 클라이언트로부터 전달받은 리뷰 데이터(주문 ID, 별점, 내용)를 검증하고 저장
 */
public class ReviewRequestDto {

  /*
   * 리뷰를 작성할 가게의 고유 식별자
   * - null 값을 허용하지 않음
   * - 유효하지 않은 경우, "가게 아이디를 입력하세요."라는 메시지와 함께 예외가 발생
   */
  @NotNull(message = "가게 아이디를 입력하세요.")
  private final Long storeId;

  /*
   * 리뷰를 작성할 주문의 고유 식별자
   * - null 값을 허용하지 않음
   * - 유효하지 않은 경우, "주문 아이디를 입력하세요."라는 메시지와 함께 예외가 발생
   */
  @NotNull(message = "주문 아이디를 입력하세요.")
  private final Long orderId;

  /*
   * 리뷰의 별점
   * - null 값을 허용하지 않음
   * - 최소값은 1, 최대값은 5로 설정
   * - 유효하지 않은 경우, 각각 "Rate는 최소 1이어야 합니다." 또는 "Rate는 최대 5이어야 합니다."라는 메시지와 함께 예외가 발생
   */
  @NotNull(message = "별점을 입력해 주세요.")
  @Min(value = 1, message = "Rate는 최소 1이어야 합니다.")
  @Max(value = 5, message = "Rate는 최대 5이어야 합니다.")
  private final Integer rate;

  /**
   * 리뷰 내용
   * - null, 빈 문자열, 공백만 포함된 문자열을 허용하지 않음
   * - 최소 10자, 최대 200자까지 작성
   * - 유효하지 않은 경우, "Content는 필수입니다." 또는 "리뷰 내용은 10자 부터 200자 까지입니다."라는 메시지와 함께 예외가 발생
   */
  @NotBlank(message = "Content는 필수입니다.")
  @Size(min = 10, max = 200, message = "리뷰 내용은 10자 부터 200자 까지입니다.")
  private final String content;

  /**
   * ReviewRequestDto 객체를 생성합니다.
   * @param storeId 리뷰를 작성할 가게의 고유 식별자
   * @param orderId 리뷰를 작성할 주문의 고유 식별자
   * @param rate    리뷰의 별점 (1~5 사이의 값)
   * @param content 리뷰의 내용 (10~200자)
   */
  public ReviewRequestDto(Long storeId, Long orderId, Integer rate, String content) {
    this.storeId = storeId;
    this.orderId = orderId;
    this.rate = rate;
    this.content = content;
  }
}


