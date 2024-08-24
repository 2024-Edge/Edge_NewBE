package com.hanium.edge.service;

import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity registerUser(String username, String password, String name) {
        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .name(name)
                .build();
        return userRepository.save(userEntity);
    }

    public UserEntity loginUser(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username).orElse(null);
        if (userEntity != null && bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
            return userEntity;
        }
        return null;
    }
}
