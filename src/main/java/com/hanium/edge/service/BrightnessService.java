package com.hanium.edge.service;

import com.hanium.edge.entity.BrightnessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hanium.edge.repository.BrightnessRepository;

@Service
public class BrightnessService {

    private final BrightnessRepository brightnessRepository;

    @Autowired
    public BrightnessService(BrightnessRepository brightnessRepository) {
        this.brightnessRepository = brightnessRepository;
    }

    public float getBrightness() {
        BrightnessEntity brightnessEntity = brightnessRepository.findById(1L).orElseThrow(() -> new RuntimeException("Brightness not found"));
        return brightnessEntity.getBrightness();
    }

    public void setBrightness(float brightness) {
        BrightnessEntity brightnessEntity = brightnessRepository.findById(1L).orElseGet(() -> {
            BrightnessEntity newEntity = new BrightnessEntity();
            newEntity.setId(1L);
            return newEntity;
        });
        brightnessEntity.setBrightness(brightness);
        brightnessRepository.save(brightnessEntity);
    }
}
