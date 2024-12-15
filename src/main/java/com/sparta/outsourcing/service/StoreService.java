package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.store.CreateStoreResponseDto;
import com.sparta.outsourcing.dto.store.UpdateStoreResponseDto;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.repository.StoreRepository;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

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

    public UpdateStoreResponseDto updateStore(Long storeId, String name, LocalTime openTime, LocalTime closeTime,Integer minOrderPrice) {
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
}
