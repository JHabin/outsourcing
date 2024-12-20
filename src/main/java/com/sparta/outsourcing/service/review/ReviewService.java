package com.sparta.outsourcing.service.review;

import com.sparta.outsourcing.dto.review.ReviewResponseDto;
import com.sparta.outsourcing.entity.Menu;
import com.sparta.outsourcing.entity.Order;
import com.sparta.outsourcing.entity.Review;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.repository.OrderRepository;
import com.sparta.outsourcing.repository.StoreRepository;
import com.sparta.outsourcing.repository.menu.MenuRepository;
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
  private final StoreRepository storeRepository;
  private final MenuRepository menuRepository;
  private final OrderRepository orderRepository;

  /*
   * 리뷰를 생성하고 저장한 후, 저장된 리뷰 정보를 반환하기 위한 메서드
   */
  public ReviewResponseDto createReview(Long storeId, Long orderId, Integer rate, String content) {

    // 주문 정보 가져오기
    Order findOrder = orderRepository.findByIdOrElseThrow(orderId);

    Store store = findOrder.getMenu().getStore();

    // 주문이 해당 가게의 주문인지 확인
    isMenuInStore(storeId, store.getId());

//    Review saveReview = reviewRepository.save(new Review(rate,content,findOrder, user,store));
//    return new ReviewResponseDto(
//        saveReview.getId(),
//        saveReview.getRate(),
//        saveReview.getContent(),
//        saveReview.getCreatedAt(),
//        saveReview.getStore().getId(),
//        saveReview.getOrder().getId(),
//        saveReview.getUser().getId());
    return null;
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
  //해당 메뉴가 가게에 있는 메뉴인지 검증 메서드 (임시)
  private void isMenuInStore(Long storeId, Long menuStoreId) {
    if (!storeId.equals(menuStoreId)) {
      throw new IllegalArgumentException("해당 가게의 주문에만 리뷰를 작성할 수 있습니다.");
    }
  }
}
