package com.hanium.edge.service;

import com.hanium.edge.entity.DustEntity;
import com.hanium.edge.repository.SenserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SenserRepository senserRepository;

    public float getDustDensity() {
        Optional<DustEntity> dustEntity = senserRepository.findById(1L);
        return dustEntity.map(DustEntity::getDust).orElse(0.0f);
    }

    public void updateDustDensity(float newDustDensity) {
        DustEntity dustEntity = senserRepository.findById(1L).orElse(new DustEntity());
        dustEntity.setDust(newDustDensity);
        dustEntity.setId(1L);
        senserRepository.save(dustEntity);
    }

}
