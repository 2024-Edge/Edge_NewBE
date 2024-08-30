package com.hanium.edge.repository;

import com.hanium.edge.entity.HumidityEntity;
import com.hanium.edge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HumidityRepository extends JpaRepository<HumidityEntity, Long> {
    Optional<HumidityEntity> findByUser(UserEntity user);
}
