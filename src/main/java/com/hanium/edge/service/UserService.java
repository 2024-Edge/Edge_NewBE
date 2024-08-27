package com.hanium.edge.service;

import com.hanium.edge.dto.RegisterDTO;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.DuplicateUsernameException;
import com.hanium.edge.repository.UserRepository;
import com.hanium.edge.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void register(RegisterDTO registerDTO) {

        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            throw new DuplicateUsernameException("중복된 아이디가 존재합니다.");
        }

        UserEntity user = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .build();

        userRepository.save(user);
    }
}