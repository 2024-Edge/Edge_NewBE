package com.hanium.edge.service;

import com.hanium.edge.entity.DustEntity;
import com.hanium.edge.repository.DustRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DustService {

    @Autowired
    private DustRepository dustRepository;

    public float getDustDensity() {
        DustEntity dustEntity = dustRepository.findById(1L).orElseThrow(() -> new RuntimeException("DustEntity not found"));
        return dustEntity.getDust();
    }

    public void updateDustDensity(float dustDensity) {
        DustEntity dustEntity = dustRepository.findById(1L).orElse(new DustEntity());
        dustEntity.setDust(dustDensity);
        dustRepository.save(dustEntity);
    }
}