package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.store.CreateStoreRequestDto;
import com.sparta.outsourcing.dto.store.CreateStoreResponseDto;
import com.sparta.outsourcing.dto.store.UpdateStoreReguestDto;
import com.sparta.outsourcing.dto.store.UpdateStoreResponseDto;
import com.sparta.outsourcing.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController // Controller + ResponseBody. JSON 형식의 데이터를 반환할 수 있게 해준다.
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    // 가게 생성
    @PostMapping
    public ResponseEntity<CreateStoreResponseDto> createStore(@RequestBody CreateStoreRequestDto createStoreRequestDto){
        System.out.println("Controller closeTime: " + createStoreRequestDto.getCloseTime());
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
    public ResponseEntity<UpdateStoreResponseDto> updateStore(@PathVariable Long storeId, @RequestBody UpdateStoreReguestDto updateStoreReguestDto){
        // 수정하려는 가게의 유저 아이디와 로그인된 유저 아이디 일치 확인 로직 추가?

        UpdateStoreResponseDto updatedStore = storeService.updateStore(
                storeId,
                updateStoreReguestDto.getName(),
                updateStoreReguestDto.getOpenTime(),
                updateStoreReguestDto.getCloseTime(),
                updateStoreReguestDto.getMinOrderPrice());
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

}
