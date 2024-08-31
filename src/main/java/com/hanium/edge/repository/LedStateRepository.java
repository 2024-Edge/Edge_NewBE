package com.hanium.edge.repository;

import com.hanium.edge.entity.LedStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LedStateRepository extends JpaRepository<LedStateEntity, Long> {
    Optional<LedStateEntity> findByLedId(int ledId);
    List<LedStateEntity> findAllByDate(String date);
}
