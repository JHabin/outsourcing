package com.sparta.outsourcing.service;

import com.sparta.outsourcing.common.Status;
import com.sparta.outsourcing.dto.order.*;
import com.sparta.outsourcing.entity.Menu;
import com.sparta.outsourcing.entity.Order;
import com.sparta.outsourcing.exception.BadValueException;
import com.sparta.outsourcing.exception.WrongAccessException;
import com.sparta.outsourcing.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * Service Class
 * 비즈니스 로직 구현 파트
 */
@Service
@RequiredArgsConstructor //의존성 주입
public class OrderService {
    private final OrderRepository orderRepository;
    //private final UserRepository userRepository;
    //private final MenuRepository menuRepository;

    /**
     * 주문 생성
     * request(필수) : menuId, count
     */
    @Transactional
    public CreateOrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto) {
//        Menu menu = menuRepository.findByIdOrElseThrow(createOrderRequestDto.getMenuId());
//
//        //영업시간이 아닐 시 예외처리
//        LocalTime nowLocalTime = LocalTime.now(); //현재시간
//        LocalTime storeOpenTime = menu.getStore().getOpenTime(); //가게 오픈시간
//        LocalTime storeCloseTime = menu.getStore().getCloseTime(); //가게 마감시간
//
//        if (nowLocalTime.isBefore(storeOpenTime) || nowLocalTime.isAfter(storeCloseTime)) {
//            //throw new WrongAccessException(ErrorCode.NOT_OPEN_STORE); //ErrorCode에 NOT_OPEN_STORE 추가 필요**
//        }
//
//        //주문금액 계산 및 최소주문금액 충족하지 못할 시 예외처리
//        Integer totalPrice = createOrderRequestDto.getCount() * menu.getPrice(); //총 주문금액
//        Integer minOrderPrice = menu.getStore().getMinOrderPrice(); //가게의 최소주문금액
//
//        if (totalPrice < minOrderPrice) {
//            throw new BadValueException(ErrorCode.LESS_THAN_MINPRICE); //ErrorCode 추가 필요**
//        }
//
//        //이상 없을 시, 주문 등록 처리
//        User user= userService.findUserById(createOrderRequestDto.getUserId()); //email로도 유저 조회 가능
//        Order order = new Order(user, menu, createOrderRequestDto.getCount(), totalPrice, Status.ORDER_COMPLETE);
//        Order savedOrder = orderRepository.save(order);

//        return new CreateOrderResponseDto(
//                savedOrder.getUser().getId(),
//                savedOrder.getMenu().getId(),
//                savedOrder.getCount(),
//                savedOrder.getTotalPrice(),
//                savedOrder.getStatus(),
//                savedOrder.getRejectedReason(),
//                savedOrder.getCreatedAt(),
//                savedOrder.getModifiedAt()
//        );
        return null;
    }

    /**
     * 주문 상태 변경
     * @PathVariable Long id, @RequestBody String status
     */
    @Transactional
    public ModifyOrderStatusResponseDto updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findByIdOrElseThrow(id);
        order.update(status); //변경감지동작

        return new ModifyOrderStatusResponseDto(
                order.getUser().getId(),
                order.getMenu().getId(),
                order.getCount(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getModifiedAt()
                );
    }

    /**
     * 주문 거절
     * @PathVariable Long id, @RequestBody String rejectReason
     */
    @Transactional
    public RejectOrderResponseDto rejectOrderStatus(Long id, String rejectReason) {
        //1. 주문 조회
        Order order = orderRepository.findByIdOrElseThrow(id);
        //2. 주문 수정 (주문 거절)
        //   2-1. 거절 사유 추가
        //order.update(rejectReason);
        //   2-2. 거절 시간 추가 (soft delete)
        return new RejectOrderResponseDto(
                order.getUser().getId(),
                order.getMenu().getId(),
                order.getCount(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getRejectedReason(),
                order.getCreatedAt(),
                order.getModifiedAt(),
                order.getDeletedAt()
        );
    }

    /**
     * 주문 단건 조회
     * @PathVariable Long id
     */
    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findByIdOrElseThrow(id);
        return new OrderResponseDto(
                order.getUser().getId(),
                order.getMenu().getId(),
                order.getCount(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getRejectedReason(),
                order.getCreatedAt(),
                order.getModifiedAt()
        );
    }

    /**
     * 주문 다건 조회
     * @RequestParam
     */
    public List<OrderResponseDto> findAll() {
        //return orderRepository.findAllByStore(store).stream().map(OrderResponseDto::toDto).toList();
        return null;
    }


}
