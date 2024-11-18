package com.example.hotelbluebird.repository;

import com.example.hotelbluebird.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
