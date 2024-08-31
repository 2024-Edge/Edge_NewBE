package com.hanium.edge.repository;

import com.hanium.edge.entity.PowerEntity;
import com.hanium.edge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerRepository extends JpaRepository<PowerEntity, Long> {
    List<PowerEntity> findByUser(UserEntity user);
    long countByUser(UserEntity user);
}
