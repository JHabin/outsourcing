package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.store.*;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
//    private final MenuRepository menuRepository;

    public CreateStoreResponseDto createStore(String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {

        Store store = new Store(name, openTime, closeTime, minOrderPrice);
//        store.setUser(findUser); // findUser -> 어떻게?

        storeRepository.save(store);

        return new CreateStoreResponseDto(
                store.getId(),
                store.getUser().getId(),
                store.getName(),
                store.getOpenTime(),
                store.getCloseTime(),
                store.getMinOrderPrice(),
                store.getCreatedAt()
        );
    }

    // TODO 수정하려는 가게의 유저 아이디와 로그인된 유저 아이디 일치 확인 로직 추가
    public UpdateStoreResponseDto updateStore(Long storeId, String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);

        findStore.updateStore(name, openTime, closeTime, minOrderPrice);
        storeRepository.save(findStore);

        return new UpdateStoreResponseDto(
                findStore.getId(),
                findStore.getUser().getId(),
                findStore.getName(),
                findStore.getOpenTime(),
                findStore.getCloseTime(),
                findStore.getMinOrderPrice()
        );
    }

    // 가게 단건 조회 로직
//    public StoreMenuResponseDto findStoreById(Long storeId) {
//        Store findStore = storeRepository.findByIdOrElseThrow(storeId);
//        List<MenuDto> menuDtoList = menuRepository.findMenuById(storeId);
//        return new StoreMenuResponseDto(
//                findStore.getId(),
//                findStore.getName(),
//                findStore.getOpenTime(),
//                findStore.getCloseTime(),
//                findStore.getMinOrderPrice(),
//                menuDtoList
//        );
//    }

    public List<StoreResponseDto> findStore(String storeName) {
        return storeRepository.findAllByStoreName(storeName);
    }
}
