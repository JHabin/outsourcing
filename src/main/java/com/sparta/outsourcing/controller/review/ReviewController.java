package com.sparta.outsourcing.controller.review;

import com.sparta.outsourcing.dto.review.ReviewRequestDto;
import com.sparta.outsourcing.dto.review.ReviewResponseDto;
import com.sparta.outsourcing.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @Controller + @ResponseBody , 응답할 Data가 있는 경우
 */
@RestController

/*
 * 생성자 의존성 주입을 자동으로 처리
 */
@RequiredArgsConstructor
public class ReviewController {

  /*
   * 리뷰 관련 비즈니스 로직을 처리하는 서비스 객체.
   * 생성자를 통해 의존성을 주입
   */
  private final ReviewService reviewService;

  /**
   * 리뷰 생성 Controller
   * 리뷰 생성 요청을 처리
   * @param requestDto 클라이언트로부터 전달받은 리뷰 생성 요청 데이터를 담은 DTO
   * @return 생성된 리뷰 데이터를 포함한 응답 객체와 HTTP 상태코드 201(CREATED)
   */
  @PostMapping("/reviews")
  public ResponseEntity<ReviewResponseDto> createReview(@Valid @RequestBody ReviewRequestDto requestDto) {
    ReviewResponseDto reviewResponseDto = reviewService.createReview(requestDto.getStoreId(), requestDto.getOrderId(), requestDto.getRate(), requestDto.getContent());
    return new ResponseEntity<>(reviewResponseDto, HttpStatus.CREATED);
  }

  /**
   * 리뷰 전체 조회 Controller
   * 모든 리뷰 데이터를 조회
   * @return 조회된 리뷰 데이터 리스트와 HTTP 상태코드 200(OK)
   */
  @GetMapping("/stores/{storeId}/reviews")
  public ResponseEntity<List<ReviewResponseDto>> findAll(@PathVariable Long storeId) {
    List<ReviewResponseDto> ReviewResponsDtoList = reviewService.findAll(storeId);
    return new ResponseEntity(ReviewResponsDtoList, HttpStatus.OK);
  }
}
