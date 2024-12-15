package com.sparta.outsourcing.repository;

import com.sparta.outsourcing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByUserOrElseThrow(String email);
}
