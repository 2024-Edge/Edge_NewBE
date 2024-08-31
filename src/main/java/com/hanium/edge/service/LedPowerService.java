package com.hanium.edge.service;

import com.hanium.edge.dto.LedPowerDTO;
import com.hanium.edge.entity.LedPowerEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.UserNotFoundException;
import com.hanium.edge.repository.LedPowerRepository;
import com.hanium.edge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LedPowerService {

    private final LedPowerRepository ledPowerRepository;
    private final UserRepository userRepository;

    public List<LedPowerDTO> getDailyLedPower(String username, LocalDate date) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        List<LedPowerEntity> powerEntities = ledPowerRepository.findAllByUserAndDate(user, date);

        return powerEntities.stream()
                .map(entity -> new LedPowerDTO(
                        entity.getLedName(),
                        entity.getCurrentPower(),
                        entity.getTotalPower(),
                        entity.getDate()
                ))
                .collect(Collectors.toList());
    }
}
