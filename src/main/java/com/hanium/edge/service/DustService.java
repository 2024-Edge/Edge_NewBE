package com.hanium.edge.service;

import com.hanium.edge.entity.BrightnessEntity;
import com.hanium.edge.entity.DustEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.SensorNotFoundException;
import com.hanium.edge.exception.UserNotFoundException;
import com.hanium.edge.repository.BrightnessRepository;
import com.hanium.edge.repository.DustRepository;
import com.hanium.edge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DustService {

    private final DustRepository dustRepository;
    private final UserRepository userRepository;

    public float getDustDensity(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        DustEntity dustEntity = dustRepository.findByUser(user)
                .orElseThrow(() -> new SensorNotFoundException("미세먼지 데이터가 없습니다."));
        return dustEntity.getDust();
    }

    public void setDustDensity(String username, float dustDensity) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        DustEntity dustEntity = dustRepository.findByUser(user)
                .orElse(new DustEntity());
        dustEntity.setDust(dustDensity);
        dustEntity.setUser(user);
        dustRepository.save(dustEntity);
    }
}