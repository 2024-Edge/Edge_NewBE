package com.hanium.edge.repository;

import com.hanium.edge.entity.BrightnessEntity;
import com.hanium.edge.entity.HumidityEntity;
import com.hanium.edge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrightnessRepository extends JpaRepository<BrightnessEntity, Long> {
    Optional<BrightnessEntity> findByUser(UserEntity user);

}