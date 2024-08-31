package com.hanium.edge.service;

import com.hanium.edge.dto.user.RegisterDTO;
import com.hanium.edge.dto.user.UserResponseDTO;
import com.hanium.edge.entity.LevelEntity;
import com.hanium.edge.entity.PowerEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.DuplicateUsernameException;
import com.hanium.edge.repository.LevelRepository;
import com.hanium.edge.repository.PowerRepository;
import com.hanium.edge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LevelRepository levelRepository;
    private final PowerRepository powerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String name = registerDTO.getName();

        if (userRepository.existsByUsername(username)) {
            throw new DuplicateUsernameException("중복된 아이디가 존재합니다.");
        }

        UserEntity user = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .name(name)
                .build();

        userRepository.save(user);

        // LevelEntity 초기값 설정
        LevelEntity levelEntity = LevelEntity.builder()
                .user(user)
                .targetPower(0)
                .actualPower(0)
                .sproutLevel(1)
                .updatedAt(LocalDate.now())
                .build();

        levelRepository.save(levelEntity);

        // PowerEntity 초기값 설정
        long count = powerRepository.countByUser(user);
        if (count == 0) {
            // 각 사용자에게 기본 장치 4개 추가
            powerRepository.save(new PowerEntity(null, "에어컨", false, user));
            powerRepository.save(new PowerEntity(null, "가습기", false, user));
            powerRepository.save(new PowerEntity(null, "공기청정기", false, user));
            powerRepository.save(new PowerEntity(null, "거실 전등", false, user));
        }
    }

    public UserResponseDTO mypage(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));
        return new UserResponseDTO(user.getUsername(), user.getName());
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }

    public void deleteUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));
        userRepository.delete(user);
    }

}
