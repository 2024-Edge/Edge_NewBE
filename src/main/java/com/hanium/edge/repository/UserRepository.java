package com.hanium.edge.repository;

import com.hanium.edge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}