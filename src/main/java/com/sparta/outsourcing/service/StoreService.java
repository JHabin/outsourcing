package com.sparta.outsourcing.service;

import com.sparta.outsourcing.common.Authentication;
import com.sparta.outsourcing.dto.store.*;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.entity.User;
import com.sparta.outsourcing.exception.UnauthorizedException;
import com.sparta.outsourcing.repository.StoreRepository;
import com.sparta.outsourcing.repository.UserRepository;
import com.sparta.outsourcing.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    public CreateStoreResponseDto createStore(Authentication authentication, String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {

        String email = authentication.getEmail();
        User user = userRepository.findByEmailOrElseThrow(email);
        Store store = new Store(name, openTime, closeTime, minOrderPrice, user);
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

    // @Transactional -> 하나의 작업이라도 실패하면 모든 작업을 롤백하여 데이터 일관성을 유지한다.
    @Transactional
    public UpdateStoreResponseDto updateStore(Authentication authentication, Long storeId, String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        String email = authentication.getEmail();
        User user = userRepository.findByEmailOrElseThrow(email);
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);

        // 로그인된 유저 아이디와 수정하려는 가게의 유저 아이디 일치 확인(본인 가게만 수정)
        if(!user.getId().equals(findStore.getUser().getId())) {
            throw new UnauthorizedException(HttpStatus.FORBIDDEN, "본인 가게만 수정이 가능합니다.");
        }

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
    public StoreMenuResponseDto findStoreById(Long storeId) {
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);
        List<MenuDto> menuDtoList = menuRepository.findByStoreId(storeId)
                .stream()
                .map(menu -> new MenuDto(menu.getName(), menu.getPrice()))
                .collect(Collectors.toList());

        return new StoreMenuResponseDto(
                findStore.getId(),
                findStore.getName(),
                findStore.getOpenTime(),
                findStore.getCloseTime(),
                findStore.getMinOrderPrice(),
                menuDtoList
        );
    }

    public List<StoreResponseDto> findStore(String storeName) {

        // storeName이 있을 경우 이름을 기준으로 매장을 조회한다.
        if (storeName != null && !storeName.isEmpty()) {
            return storeRepository.findAllByStoreName(storeName);
        }

        // storeName이 null 또는 빈문자열이면 전체 매장을 조회한다.
        List<Store> stores = storeRepository.findAll();
        List<StoreResponseDto> storeResponseDtoList = new ArrayList<>();

        // TODO 팀원들과 코드 형식 맞추기
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
    public void deleteStore(Authentication authentication, Long storeId) {
        String email = authentication.getEmail();
        User user = userRepository.findByEmailOrElseThrow(email);
        Store findStore = storeRepository.findByIdOrElseThrow(storeId);

        // 삭제하려는 가게의 유저 아이디와 로그인된 유저 아이디가 일치하는 지 확인(본인 가게만 삭제 가능)
        if(!user.getId().equals(findStore.getUser().getId())) {
            throw new UnauthorizedException(HttpStatus.FORBIDDEN, "본인 가게만 삭제 가능합니다.");
        }

        storeRepository.delete(findStore);
    }
}
