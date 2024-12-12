package com.sparta.outsourcing.entity;

import com.sparta.outsourcing.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Entity
@Table(name = "store")
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
    private Integer minPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}