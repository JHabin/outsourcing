package com.sparta.outsourcing.repository;

import com.sparta.outsourcing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByIdOrElseThrows(Long id);
    User findByEmail(String email);
}
