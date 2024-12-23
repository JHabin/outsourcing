package com.sparta.outsourcing.repository.order;

import com.sparta.outsourcing.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    default Order findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"주문이 존재하지 않습니다."));
    }
}
