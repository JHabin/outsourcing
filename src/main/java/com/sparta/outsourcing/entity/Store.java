package com.sparta.outsourcing.entity;

import com.sparta.outsourcing.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Entity
@Table(name = "`store`")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, name = "open_time")
    private LocalTime openTime;

    @Column(nullable = false, name = "close_time")
    private LocalTime closeTime;

    @Column(nullable = false, name = "min_order_price")
    private Integer minOrderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Store(String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
    }

    public Store() {
    }

    public Store(String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice, User user) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
        this.user = user;
    }

    public void updateStore(String name, LocalTime openTime, LocalTime closeTime, Integer minOrderPrice) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minOrderPrice = minOrderPrice;
    }
}