package com.sparta.outsourcing.service.review;

import com.sparta.outsourcing.dto.review.ReviewResponseDto;
import com.sparta.outsourcing.entity.Review;
import com.sparta.outsourcing.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Service 계층 , 비즈니스 로직 처리
 */
@Service
/*
 * 생성자 의존성 주입
 */
@RequiredArgsConstructor
public class ReviewService {
  private final ReviewRepository reviewRepository;

  /*
   * 리뷰를 생성하고 저장한 후, 저장된 리뷰 정보를 반환하기 위한 메서드
   */
  public ReviewResponseDto createReview(Long storeId, Long orderId, Integer rate, String content) {
    Review saveReview = reviewRepository.save(new Review(rate, content));
    return new ReviewResponseDto(
        saveReview.getId(),
        saveReview.getRate(),
        saveReview.getContent(),
        saveReview.getCreatedAt(),
        saveReview.getStore().getId(),
        saveReview.getOrder().getId(),
        saveReview.getUser().getId());
  }

  /*
   * 모든 리뷰 데이터를 조회한 후, 각 리뷰를 DTO로 변환하여 반환하는 메서드
   */
  public List<ReviewResponseDto> findAll(Long storeId) {

    return reviewRepository.findAllByStoreId(storeId)
        .stream()
        .map(ReviewResponseDto::toDto)
        .toList();
  }
}
