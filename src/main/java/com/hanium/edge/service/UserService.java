package com.hanium.edge.service;

import com.hanium.edge.dto.user.RegisterDTO;
import com.hanium.edge.dto.user.UserResponseDTO;
import com.hanium.edge.entity.PowerEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.DuplicateUsernameException;
import com.hanium.edge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
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
