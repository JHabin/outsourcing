package com.sparta.outsourcing.entity;

import com.sparta.outsourcing.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

/*
 * 클래스의 필드에 대해 Getter 메서드를 자동으로 생성
 */
@Getter
/*
 * 해당 클래스를 JPA 엔티티(Entity)로 지정하는 데 사용
 * 데이터베이스 테이블에 매핑되는 클래스임
 */
@Entity
/*
 * 엔티티 클래스와 데이터베이스의 테이블을 매핑하는 데 사용
 */
@Table(name = "`menu`")
/*
 * 시스템에서 메뉴 항목을 나타내는 클래스
 * Menu는 특정 Store(가게)에 속하며, 이름, 가격, 가게 정보 등의 세부 정보를 포함
 */
public class Menu extends BaseEntity {

  /*
   * 메뉴의 고유 식별자
   * 이 ID는 데이터베이스에서 자동으로 생성, 자동으로 1씩 증가
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /*
   * 메뉴 컬럼의 이름
   * null 값을 허용하지 않으며, 최대 50자까지 입력 가능
   */
  @Column(nullable = false, length = 50)
  private String name;

  /*
   * 메뉴 항목의 가격
   * null 값을 허용하지 않는다
   */
  @Column(nullable = false)
  private Integer price;

  /*
   * 이 메뉴가 속한 가게(Store)
   * Many-to-One 관계로 매핑되며, 외래 키는 `store_id` 컬럼에 저장
   * null 값을 허용하지 않는다
   */
  @ManyToOne
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  /**
   * 메뉴 항목의 이름을 업데이트
   *
   * @param menuName 업데이트할 새로운 메뉴 이름
   */
  public void updateMenu(String menuName) {
    this.name = menuName;
  }

  /**
   * 지정된 이름, 가격 및 가게 정보를 사용하여 새로운 Menu 객체를 생성합니다.
   *
   * @param name  메뉴 항목의 이름
   * @param price 메뉴 항목의 가격
   * @param store 메뉴가 속한 가게
   */
  public Menu(String name, Integer price, Store store) {
    this.name = name;
    this.price = price;
    this.store = store;
  }

  /*
   * JPA에서 사용되는 기본 생성자
   */
  public Menu() {

  }
}
