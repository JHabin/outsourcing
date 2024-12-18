package com.sparta.outsourcing.dto.review;

import com.sparta.outsourcing.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

/*
 * 클래스의 필드에 대해 Getter 메서드를 자동으로 생성
 */
@Getter

/*
 * 리뷰 응답 데이터를 처리하기 위한 Data Transfer Object(DTO) 클래스
 * 클라이언트에게 반환할 리뷰 데이터를 담습니다. 리뷰 ID, 별점, 내용, 생성일, 주문 ID, 사용자 ID를 포함
 */
public class ReviewResponseDto {

  /*
   * 리뷰의 고유 식별자.
   */
  private final Long id;

  /*
   * 리뷰의 별점.
   */
  private final Integer rate;

  /*
   * 리뷰의 내용
   */
  private final String content;

  /*
   * 리뷰가 생성된 날짜와 시간
   */
  private final LocalDateTime createdAt;

  /*
   * 리뷰를 작성한 가게의 고유 식별자
   */
  private final Long storeId;

  /*
   * 리뷰가 작성된 주문의 고유 식별자
   */
  private final Long orderId;

  /*
   * 리뷰를 작성한 사용자의 고유 식별자
   */
  private final Long userId;

  /**
   * ReviewResponseDto 객체를 생성합니다.
   *
   * @param id        리뷰의 고유 식별자
   * @param rate      리뷰의 별점
   * @param content   리뷰의 내용
   * @param createdAt 리뷰가 생성된 날짜와 시간
   * @param storeId   리뷰가 작성된 가게의 고유 식별자
   * @param orderId   리뷰가 작성된 주문의 고유 식별자
   * @param userId    리뷰를 작성한 사용자의 고유 식별자
   */
  public ReviewResponseDto(Long id, Integer rate, String content, LocalDateTime createdAt, Long storeId, Long orderId, Long userId) {
    this.id = id;
    this.rate = rate;
    this.content = content;
    this.createdAt = createdAt;
    this.storeId = storeId;
    this.orderId = orderId;
    this.userId = userId;
  }

  /**
   * Review 엔티티를 ReviewResponseDto로 변환
   *
   * @param review 변환할 Review 엔티티
   * @return 변환된 ReviewResponseDto 객체
   */
  public static ReviewResponseDto toDto(Review review) {
    return new ReviewResponseDto(review.getId(), review.getRate(), review.getContent(), review.getCreatedAt(), review.getStore().getId(), review.getOrder().getId(), review.getUser().getId());
  }
}

