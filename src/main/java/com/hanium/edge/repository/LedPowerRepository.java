package com.hanium.edge.repository;

import com.hanium.edge.entity.LedPowerEntity;
import com.hanium.edge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LedPowerRepository extends JpaRepository<LedPowerEntity, Long> {
    Optional<LedPowerEntity> findByUserAndLedNameAndDate(UserEntity user, String ledName, LocalDate date);
    List<LedPowerEntity> findAllByUserAndDate(UserEntity user, LocalDate date);
    List<LedPowerEntity> findAllByUserAndDateBetween(UserEntity user, LocalDate startDate, LocalDate endDate);
}
