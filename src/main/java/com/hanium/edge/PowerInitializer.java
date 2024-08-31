package com.hanium.edge;

import com.hanium.edge.entity.PowerEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.repository.PowerRepository;
import com.hanium.edge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PowerInitializer {

    @Autowired
    private PowerRepository powerRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // 모든 사용자 조회
            Iterable<UserEntity> users = userRepository.findAll();

            for (UserEntity user : users) {
                // 사용자의 전력 장치가 4개가 아닌 경우에만 기본 장치 추가
                long count = powerRepository.countByUser(user);
                if (count == 0) {
                    // 각 사용자에게 기본 장치 4개 추가
                    powerRepository.save(new PowerEntity(null, "Air Conditioner", false, user));
                    powerRepository.save(new PowerEntity(null, "Humidifier", false, user));
                    powerRepository.save(new PowerEntity(null, "Air Purifier", false, user));
                    powerRepository.save(new PowerEntity(null, "Light", false, user));
                }
            }
        };
    }
}
