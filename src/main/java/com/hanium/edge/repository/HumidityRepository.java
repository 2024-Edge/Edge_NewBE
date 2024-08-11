package com.hanium.edge.repository;

import com.hanium.edge.entity.HumidityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumidityRepository extends JpaRepository<HumidityEntity, Long> {
}
