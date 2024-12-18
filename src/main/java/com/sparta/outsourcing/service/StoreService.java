package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.store.*;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
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
    // @Transactional -> 하나의 작업이라도 실패하면 모든 작업을 롤백하여 데이터 일관성을 유지한다.
    @Transactional
    public UpdateStoreResponseDto updateStore(Long storeId, String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);
        findStore.updateStore(name, openTime, closeTime, minOrderPrice);
        // 조회된 findStore 객체에서 updateStore 메서드로 데이터를 수정 -> 트랜잭션 종료 시 데이터베이스에 자동으로 커밋
        // JPA의 더티체킹(변경 감지) 매커니즘 덕분에 repository.save()를 따로 호출하지 않아도 변경 사항이 데이터베이스에 자동으로 반영된다.
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

        // storeName이 있을 경우 이름을 기준으로 매장을 조회한다.
        if (storeName != null && !storeName.isEmpty()) {
            return storeRepository.findAllByStoreName(storeName);
        }
        // storeName이 null 또는 빈문자열이면 전체 매장을 조회한다.

//            return storeRepository.findAllStores();

        // TODO findAll 메서드를 통해 조회한 데이터를 dto로 변환
        //  서비스 레이어에서 반복문 사용해서 수동으로 변환하는 방식? 레파지토리에서 쿼리문을 사용해 변환하는 방식?
        // 반복문을 사용한 수동 변환 방식
        List<Store> stores = storeRepository.findAll();
        List<StoreResponseDto> storeResponseDtoList = new ArrayList<>();

        for (Store store : stores) {
            StoreResponseDto dto = new StoreResponseDto(
                    store.getId(),
                    store.getName(),
                    store.getOpenTime(),
                    store.getCloseTime(),
                    store.getMinOrderPrice()
            );
            storeResponseDtoList.add(dto);
        }
        return storeResponseDtoList;

    }

    // TODO 권한 확인 로직 추가
    @Transactional
    public void deleteStore(Long storeId) {
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);
        storeRepository.delete(findStore);
    }
}
