package com.sparta.outsourcing.repository.menu;

import com.sparta.outsourcing.entity.Menu;
import com.sparta.outsourcing.exception.ErrorCode;
import com.sparta.outsourcing.exception.MenuException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* Repository 데이터 엑세스 계층, 데이터베이스와 통신*/
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

  /*
  * 메뉴를 ID로 조회하고, 존재하지 않을 경우 예외를 던지는 역할을 하는 메서드*/
  default Menu findByIdOrElseThrow(Long id) {
    return findById(id).orElseThrow(() -> new MenuException(ErrorCode.NOT_FOUND_MENU));
  }
}