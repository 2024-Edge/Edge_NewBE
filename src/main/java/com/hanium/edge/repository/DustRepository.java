package com.hanium.edge.repository;

import com.hanium.edge.entity.DustEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DustRepository extends JpaRepository<DustEntity, Long> {
}