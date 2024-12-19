package com.sparta.outsourcing.entity;

import com.sparta.outsourcing.common.BaseEntity;
import com.sparta.outsourcing.common.Status;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="`order`")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false, name = "total_price")
    private Integer totalPrice;

    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(nullable = true, name = "rejected_reason", length = 50)
    private String rejectedReason;

    public Order() {

    }

    public Order(User user, Menu menu, Integer count, Integer totalPrice, Status status) {
        this.user = user;
        this.menu = menu;
        this.count = count;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public void update(String status) {
        this.status = Status.valueOf(status);
    }
}