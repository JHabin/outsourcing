package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.store.*;
import com.sparta.outsourcing.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // Controller + ResponseBody. JSON 형식의 데이터를 반환할 수 있게 해준다.
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    // 가게 생성
    @PostMapping
    public ResponseEntity<CreateStoreResponseDto> createStore(@RequestBody CreateStoreRequestDto createStoreRequestDto) {
        CreateStoreResponseDto createStoreResponseDto = storeService.createStore(
                createStoreRequestDto.getName(),
                createStoreRequestDto.getOpenTime(),
                createStoreRequestDto.getCloseTime(),
                createStoreRequestDto.getMinOrderPrice()
        );

        return new ResponseEntity<>(createStoreResponseDto, HttpStatus.CREATED);
    }

    // 가게 수정
    @PatchMapping("/{storeId}")
    public ResponseEntity<UpdateStoreResponseDto> updateStore(@PathVariable Long storeId, @RequestBody UpdateStoreReguestDto updateStoreReguestDto) {
        UpdateStoreResponseDto updatedStore = storeService.updateStore(
                storeId,
                updateStoreReguestDto.getName(),
                updateStoreReguestDto.getOpenTime(),
                updateStoreReguestDto.getCloseTime(),
                updateStoreReguestDto.getMinOrderPrice());
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    // 가게 단건 조회
//    @GetMapping("/{storeId}")
//    public ResponseEntity<StoreMenuResponseDto> findStoreById(@PathVariable Long storeId){
//        StoreMenuResponseDto storeMenuResponseDto = storeService.findStoreById(storeId);
//        return new ResponseEntity<>(storeMenuResponseDto, HttpStatus.OK);
//    }

    // 가게 다건 조회
    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> findStore(@RequestParam String storeName) {
        List<StoreResponseDto> storeResponseDtoList = storeService.findStore(storeName);
        return new ResponseEntity<>(storeResponseDtoList, HttpStatus.OK);
    }
}
