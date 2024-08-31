package com.hanium.edge.repository;

import com.hanium.edge.entity.LevelEntity;
import com.hanium.edge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<LevelEntity, Long> {
    Optional<LevelEntity> findByUser(UserEntity user);
}
