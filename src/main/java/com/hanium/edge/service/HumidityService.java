package com.hanium.edge.service;

import com.hanium.edge.entity.HumidityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hanium.edge.repository.HumidityRepository;

@Service
public class HumidityService {

    private final HumidityRepository humidityRepository;

    @Autowired
    public HumidityService(HumidityRepository humidityRepository) {
        this.humidityRepository = humidityRepository;
    }

    public float getTemperature() {
        HumidityEntity humidityEntity = humidityRepository.findById(1L).orElseThrow(() -> new RuntimeException("Humidity not found"));
        return humidityEntity.getTemperature();
    }

    public void setTemperature(float temperature) {
        HumidityEntity humidityEntity = humidityRepository.findById(1L).orElse(new HumidityEntity());
        humidityEntity.setTemperature(temperature);
        humidityRepository.save(humidityEntity);
    }

    public float getHumidity() {
        HumidityEntity humidityEntity = humidityRepository.findById(1L).orElseThrow(() -> new RuntimeException("Humidity not found"));
        return humidityEntity.getHumidity();
    }

    public void setHumidity(float humidity) {
        HumidityEntity humidityEntity = humidityRepository.findById(1L).orElseGet(() -> {
            HumidityEntity newEntity = new HumidityEntity();
            newEntity.setId(1L);
            return newEntity;
        });
        humidityEntity.setHumidity(humidity);
        humidityRepository.save(humidityEntity);
    }
}