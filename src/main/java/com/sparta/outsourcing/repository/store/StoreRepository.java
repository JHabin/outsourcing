package com.sparta.outsourcing.repository.store;

import com.sparta.outsourcing.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    default Store findByIdOrElseThrow(Long storeId) {
        return findById(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "가게가 존재하지 않습니다."));
    }

    //  JPQL에서 DTO를 생성하려면 DTO의 풀 패키지 경로를 명시적으로 작성해야한다.
//    @Query("SELECT new com.sparta.outsourcing.dto.store.StoreResponseDto(s.id, s.name, s.openTime, s.closeTime, s.minOrderPrice) " +
//            "FROM Store s WHERE s.name LIKE %:storeName%")
//    List<StoreResponseDto> findAllByStoreName(@Param("storeName") String storeName);

    List<Store> findAll();

    @Query("select s from Store s where s.user.id = :ownerId")
    List<Store> findAllById(@Param("ownerId") Long ownerId);

    @Query("SELECT COUNT(s) FROM Store s WHERE s.user.id = :ownerId")
    long countStoresById(@Param("ownerId") Long ownerId);
}
