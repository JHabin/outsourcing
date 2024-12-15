package com.sparta.outsourcing.repository;

import com.sparta.outsourcing.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    default Store findByIdOrElseThrow(Long storeId) {
        return findById(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "가게가 존재하지 않습니다."));
    }
}
