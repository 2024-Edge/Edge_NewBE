package com.hanium.edge;

import com.hanium.edge.entity.LevelEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.repository.LevelRepository;
import com.hanium.edge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LevelInitializer {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean(name = "levelDataLoader")
    public CommandLineRunner loadData() {
        return args -> {
            // 모든 사용자 조회
            Iterable<UserEntity> users = userRepository.findAll();

            for (UserEntity user : users) {
                // 레벨 엔티티가 이미 존재하는지 확인
                if (!levelRepository.findByUser(user).isPresent()) {
                    LevelEntity levelEntity = LevelEntity.builder()
                            .user(user)
                            .targetPower(0.0)
                            .actualPower(0.0)
                            .sproutLevel(1)
                            .updatedAt(LocalDate.now())
                            .build();

                    levelRepository.save(levelEntity);
                }
            }
        };
    }
}
