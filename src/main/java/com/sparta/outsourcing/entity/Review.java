package com.sparta.outsourcing.entity;

import com.sparta.outsourcing.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;



/*
 * 클래스의 필드에 대해 Getter 메서드를 자동으로 생성해 줍니다.
 */
@Getter
/*
 * 해당 클래스를 JPA 엔티티(Entity)로 지정하는 데 사용
 * 데이터베이스 테이블에 매핑되는 클래스임을 나타냅니다.
 */
@Entity
/*
 * 엔티티 클래스와 데이터베이스의 테이블을 매핑하는 데 사용
 */
@Table(name="`review`")

/*
 * Review 엔티티 클래스.
 * 리뷰 데이터를 저장하며, 작성된 리뷰는 사용자(User)와 주문(Order)에 연관
 */
public class Review extends BaseEntity {

    /*
     * 메뉴의 고유 식별자
     * 이 ID는 데이터베이스에서 자동으로 생성, 자동으로 1씩 증가
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * 리뷰의 평점을 나타냅니다.
     * null 값을 허용하지 않으며, 정수형(Integer)로 저장
     * 값은 1에서 5 사이여야 하는 제약
     */
    @Column(nullable = false)
    private Integer rate;


    /*
     * 리뷰의 내용
     * null 값을 허용하지 않으며, 최대 길이는 200자로 제한
     */
    @Column(nullable = false, length = 200)
    private String content;

    /*
     * 리뷰가 속한 주문(Order)과의 연관 관계
     * Many-to-One 관계로 매핑되며, 외래 키는 `order_id`로 저장
     * null 값을 허용하지 않음
     */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /*
     * 리뷰 작성자(User)와의 연관 관계
     * Many-to-One 관계로 매핑되며, 외래 키는 `user_id`로 저장
     * null 값을 허용하지 않음
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /*
     * 리뷰 가게(Store)와의 연관 관계
     * Many-to-One 관계로 매핑되며, 외래 키는 `store_id`로 저장
     * null 값을 허용하지 않음
     */
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    /**
     * 모든 필드를 초기화하여 새로운 Review 객체를 생성
     *
     * @param rate    리뷰의 평점
     * @param content 리뷰의 내용
     * @param order   리뷰가 속한 주문
     * @param user    리뷰 작성자
     * @param store   리뷰가 속한 가게
     */

    public Review(Integer rate, String content, Order order, User user, Store store) {
        this.rate = rate;
        this.content = content;
        this.order = order;
//        this.user = user;
        this.store = store;
    }

    /*
     * JPA에서 사용되는 기본 생성자입니다.
     */
    public Review() {

    }
}
