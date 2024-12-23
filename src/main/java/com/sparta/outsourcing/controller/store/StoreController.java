package com.sparta.outsourcing.controller.store;

import com.sparta.outsourcing.common.Authentication;
import com.sparta.outsourcing.constants.SessionNames;
import com.sparta.outsourcing.dto.store.*;
import com.sparta.outsourcing.service.store.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // 생성자 자동 주입
@RestController // Controller + ResponseBody. JSON 형식의 데이터를 반환할 수 있게 해준다.
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 생성 API
     *
     * @param createStoreRequestDto 생성할 가게의 정보를 담고있는 dto.
     * @return 생성된 가게의 정보를 담고 있는 dto. 성공시 상태코드 201 반환
     */
    @PostMapping
    // @Valid -> 객체의 제약 조건을 검증하도록 지시하는 어노테이션.
    // @RequestBody -> http 요청으로 받는 json을 request 객체로 바꿔준다.
    public ResponseEntity<CreateStoreResponseDto> createStore(
            @Valid @RequestBody CreateStoreRequestDto createStoreRequestDto,
            HttpServletRequest httpServletRequest
    ) {

        // 세션에 저장되어있는 Authentication 객체를 가져온다.
        HttpSession session = httpServletRequest.getSession();
        Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);

        // 새로운 매장을 생성하고 그 결과를 CreateStoreResponseDto 객체에 저장한다.
        CreateStoreResponseDto createStoreResponseDto = storeService.createStore(
                authentication,
                createStoreRequestDto.getName(),
                createStoreRequestDto.getOpenTime(),
                createStoreRequestDto.getCloseTime(),
                createStoreRequestDto.getMinOrderPrice()
        );

        return new ResponseEntity<>(createStoreResponseDto, HttpStatus.CREATED);
    }

    /**
     * 가게 수정 API
     *
     * @param storeId               수정하려는 가게의 id
     * @param updateStoreRequestDto 수정할 가게의 정보를 담고있는 dto
     * @return 수정된 가게의 정보를 담고 있는 dto. 성공시 상태코드 200 반환
     */
    @PatchMapping("/{storeId}")
    public ResponseEntity<UpdateStoreResponseDto> updateStore(
            @PathVariable Long storeId,
            @Valid @RequestBody UpdateStoreRequestDto updateStoreRequestDto,
            HttpServletRequest httpServletRequest
    ) {

        HttpSession session = httpServletRequest.getSession();
        Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);

        UpdateStoreResponseDto updatedStore = storeService.updateStore(
                authentication,
                storeId,
                updateStoreRequestDto.getName(),
                updateStoreRequestDto.getOpenTime(),
                updateStoreRequestDto.getCloseTime(),
                updateStoreRequestDto.getMinOrderPrice());
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    /**
     * 가게 단건 조회 API
     *
     * @param storeId 조회 하려는 가게의 id
     * @return 특정 가게의 정보를 담고 있는 dto. 성공시 상태코드 200 반환
     */
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreMenuResponseDto> findStoreById(@PathVariable Long storeId) {
        StoreMenuResponseDto storeMenuResponseDto = storeService.findStoreById(storeId);
        return new ResponseEntity<>(storeMenuResponseDto, HttpStatus.OK);
    }

    /**
     * 가게 다건 조회 API
     *
     * @param storeName 검색하려는 매장의 이름(선택 사항)
     * @return 조회한 가게의 리스트를 담고 있는 dto. 성공시 상태코드 200 반환.
     */
    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> findStore(@RequestParam(required = false) String storeName) {
        List<StoreResponseDto> storeResponseDtoList = storeService.findStore(storeName);
        return new ResponseEntity<>(storeResponseDtoList, HttpStatus.OK);
    }

    /**
     * 가게 삭제 API
     *
     * @param storeId 삭제할 가게의 id
     * @return 삭제 성공 시 상태코드 200 반환.
     */
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long storeId, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);
        storeService.deleteStore(authentication, storeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
