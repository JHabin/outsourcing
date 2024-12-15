package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.store.CreateStoreRequestDto;
import com.sparta.outsourcing.dto.store.CreateStoreResponseDto;
import com.sparta.outsourcing.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
