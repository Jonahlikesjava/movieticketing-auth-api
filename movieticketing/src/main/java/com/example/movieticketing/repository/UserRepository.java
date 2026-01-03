package com.example.movieticketing.repository;

import com.example.movieticketing.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // used during login to retrieve user's email for credential verification
    Optional<UserEntity> findByEmail(String email);

    // used during login to retrieve user's username for credential verification
    Optional<UserEntity> findByUsername(String username);

    // user during registration to see if email already exists
    boolean existsByEmail(String email);

    // used during registration to see if username already exists
    boolean existsByUsername(String username);
}