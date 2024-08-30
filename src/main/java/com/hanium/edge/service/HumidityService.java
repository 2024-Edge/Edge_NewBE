package com.hanium.edge.service;

import com.hanium.edge.entity.HumidityEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.UserNotFoundException;
import com.hanium.edge.exception.SensorNotFoundException;
import com.hanium.edge.repository.HumidityRepository;
import com.hanium.edge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HumidityService {

    private final HumidityRepository humidityRepository;
    private final UserRepository userRepository;

    public float getTemperature(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        HumidityEntity humidityEntity = humidityRepository.findByUser(user)
                .orElseThrow(() -> new SensorNotFoundException("온도 데이터가 없습니다."));
        return humidityEntity.getTemperature();
    }

    public void setTemperature(String username, float temperature) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        HumidityEntity humidityEntity = humidityRepository.findByUser(user)
                .orElse(new HumidityEntity());
        humidityEntity.setTemperature(temperature);
        humidityEntity.setUser(user);
        humidityRepository.save(humidityEntity);
    }

    public float getHumidity(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        HumidityEntity humidityEntity = humidityRepository.findByUser(user)
                .orElseThrow(() -> new SensorNotFoundException("습도 데이터가 없습니다."));
        return humidityEntity.getHumidity();
    }


    public void setHumidity(String username, float humidity) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        HumidityEntity humidityEntity = humidityRepository.findByUser(user)
                .orElse(new HumidityEntity());
        humidityEntity.setHumidity(humidity);
        humidityEntity.setUser(user);
        humidityRepository.save(humidityEntity);
    }

}
