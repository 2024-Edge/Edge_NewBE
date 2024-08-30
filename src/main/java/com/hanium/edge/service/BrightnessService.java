package com.hanium.edge.service;

import com.hanium.edge.entity.BrightnessEntity;
import com.hanium.edge.entity.HumidityEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.SensorNotFoundException;
import com.hanium.edge.exception.UserNotFoundException;
import com.hanium.edge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hanium.edge.repository.BrightnessRepository;

@Service
@RequiredArgsConstructor
public class BrightnessService {

    private final BrightnessRepository brightnessRepository;
    private final UserRepository userRepository;

    public float getBrightness(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        BrightnessEntity brightnessEntity = brightnessRepository.findByUser(user)
                .orElseThrow(() -> new SensorNotFoundException("광조도 데이터가 없습니다."));
        return brightnessEntity.getBrightness();
    }

    public void setBrightness(String username, float brightness) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        BrightnessEntity brightnessEntity = brightnessRepository.findByUser(user)
                .orElse(new BrightnessEntity());
        brightnessEntity.setBrightness(brightness);
        brightnessEntity.setUser(user);
        brightnessRepository.save(brightnessEntity);
    }
}