package com.hanium.edge.repository;

import com.hanium.edge.entity.DustEntity;
import com.hanium.edge.entity.HumidityEntity;
import com.hanium.edge.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DustRepository extends JpaRepository<DustEntity, Long> {
    Optional<DustEntity> findByUser(UserEntity user);

}