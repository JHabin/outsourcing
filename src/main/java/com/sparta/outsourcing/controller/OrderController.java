package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.order.*;
import com.sparta.outsourcing.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //@Controller(HTML페이지를 반환하는 역할) + @ResponseBody(화면이 아닌 데이터를 전달할 때 사용) => JSON 형식의 데이터를 반환
@RequestMapping("/orders")
@RequiredArgsConstructor //의존성 주입(new...)을 Spring이 대신 해주는것. Required는 초기화되지 않은 final 필드와 @NonNull필드에 생성자 생성 -> 인스턴스화 하지 않았는데도 orderService 쓸 수 있는 이유
public class OrderController {
    private final OrderService orderService;

    /**
     * 주문 생성 (POST)
     * request(필수) : menuId, count
     * 201(정상), 400(영업시간이 아님, 최소주문금액 미달)
     * @param createOrderRequestDto
     *        @RequestBody : 클라이언트가 보낸 JSON 형태를 java가 사용할 '객체' 형태로 변환해줌
     * @return CreateOrderResponseDto
     *         ResponseEntity : HTTP 응답 처리하는 클래스, 상태코드 응답본문 헤더 설정하여 반환할 수 있음
     *         ResponseDto 응답하면 헤더나 상태코드는 설정할 수 없음
     */
    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> createOrder(@Valid @RequestBody CreateOrderRequestDto createOrderRequestDto) {
        CreateOrderResponseDto createOrderResponseDto = orderService.createOrder(createOrderRequestDto);
        //return new ResponseEntity<>(CreateOrderRequestDto, HttpStatus.CREATED); //컨벤션 맞추기

        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderResponseDto);
    }

    /**
     * 주문 상태 수정 API (PATCH)
     * request(필수): status
     * 200(정상), 401(권한없음-OWNER만 가능), 404(주문데이터 없음)
     * @param id 주문번호
     * @param status 주문상태
     * @return ModifyOrderStatusResponseDto
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ModifyOrderStatusResponseDto> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok().body(orderService.updateOrderStatus(id, status));
    }

    /**
     * 주문 거절 API (PATCH)
     * request(필수): rejectReason
     * 200(정상), 401(권한없음-OWNER만 가능), 404(주문데이터 없음)
     * @param id 주문번호
     * @param rejectReason 거절사유
     * @return RejectOrderResponseDto
     */
    @PatchMapping("/{id}/reject")
    public ResponseEntity<RejectOrderResponseDto> rejectOrderStatus(@PathVariable Long id, @RequestBody String rejectReason) {
        return ResponseEntity.ok().body(orderService.rejectOrderStatus(id, rejectReason));
    }

    /**
     * 주문 (단건) 조회 API (GET)
     * @param id 가게 고유식별자
     * @return OrderResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.findById(id));
    }

    /**
     * 주문 (다건) 조회 API (GET)
     * 유저 기준 주문내역 조회
     * @param (email) 사용자 고유식별자
     *        RequestParam: /storeId=?
     * @return List<OrderResponseDto>
     */
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrder() {
        return ResponseEntity.ok().body(orderService.findAll());
    }
}
