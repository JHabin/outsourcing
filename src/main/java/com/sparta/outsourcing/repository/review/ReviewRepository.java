package com.sparta.outsourcing.repository.review;

import com.sparta.outsourcing.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
Repository 데이터 엑세스 계층, 데이터베이스와 통신
* */
@Repository

public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findAllByStoreId(Long storeId);
}
