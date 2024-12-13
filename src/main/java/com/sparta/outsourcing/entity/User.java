package com.sparta.outsourcing.entity;

import com.sparta.outsourcing.common.BaseEntity;
import com.sparta.outsourcing.common.Role;
import jakarta.persistence.*;
import com.sparta.outsourcing.config.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "`user`")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

//    @Convert(converter = PasswordEncoder.class)
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String email, String password, String nickname, String address, String phone, Role role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }
}
