package com.hanium.edge.service;

import com.hanium.edge.dto.user.LevelDTO;
import com.hanium.edge.dto.user.UpdateLevelDTO;
import com.hanium.edge.entity.LevelEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.LevelNotFoundException;
import com.hanium.edge.exception.UserNotFoundException;
import com.hanium.edge.repository.LevelRepository;
import com.hanium.edge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository levelRepository;
    private final UserRepository userRepository;

    public LevelDTO getLevel(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        LevelEntity levelEntity = levelRepository.findByUser(user)
                .orElseThrow(() -> new LevelNotFoundException("레벨 정보를 찾을 수 없습니다."));


        return LevelDTO.builder()
                .targetPower(levelEntity.getTargetPower())
                .actualPower(levelEntity.getActualPower())
                .sproutLevel(levelEntity.getSproutLevel())
                .build();
    }

    public void updateTargetPower(String username, UpdateLevelDTO updateLevelDTO) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        LevelEntity levelEntity = levelRepository.findByUser(user)
                .orElseThrow(() -> new LevelNotFoundException("레벨 정보를 찾을 수 없습니다."));

        levelEntity.setTargetPower(updateLevelDTO.getTargetPower());
        levelRepository.save(levelEntity);
    }

    public void updateActualPower(String username, double actualPower) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        LevelEntity levelEntity = levelRepository.findByUser(user)
                .orElseThrow(() -> new LevelNotFoundException("레벨 정보를 찾을 수 없습니다."));

        levelEntity.setActualPower(actualPower);
        levelRepository.save(levelEntity);
    }

    @Scheduled(cron = "0 0 0 1 * ?") // 매월 1일 자정에 실행
    public void updateSproutLevel() {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("updateSproutLevel 메서드 실행됨");
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        levelRepository.findAll().forEach(levelEntity -> {
            if (levelEntity.getUpdatedAt().isBefore(startOfMonth)) {
                double targetPower = levelEntity.getTargetPower();
                double actualPower = levelEntity.getActualPower();
                if (actualPower <= targetPower) {
                    levelEntity.setSproutLevel(levelEntity.getSproutLevel() + 1);
                }
                levelEntity.setUpdatedAt(endOfMonth);
                levelRepository.save(levelEntity);
            }
//            double targetPower = levelEntity.getTargetPower();
//            double actualPower = levelEntity.getActualPower();
//            logger.info("Entity ID: {}, Target Power: {}, Actual Power: {}", levelEntity.getId(), targetPower, actualPower);
//                if (actualPower <= targetPower) {
//                    levelEntity.setSproutLevel(levelEntity.getSproutLevel() + 1);
//                }
//                levelEntity.setUpdatedAt(endOfMonth);
//                levelRepository.save(levelEntity);
        });
    }

}
