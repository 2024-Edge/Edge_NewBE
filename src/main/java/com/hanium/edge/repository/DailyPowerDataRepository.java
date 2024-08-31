package com.hanium.edge.repository;

import com.hanium.edge.entity.DailyPowerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DailyPowerDataRepository extends JpaRepository<DailyPowerDataEntity, Long> {
    Optional<DailyPowerDataEntity> findByDateAndLedId(String date, int ledId);
    List<DailyPowerDataEntity> findAllByDate(String date);
    float getTotalPowerForAllLeds(String date);
    void updateTotalPowerForAllLeds(String date);
}
