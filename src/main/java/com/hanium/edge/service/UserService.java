package com.hanium.edge.service;

import com.hanium.edge.dto.RegisterRequest;
import com.hanium.edge.model.User;
import com.hanium.edge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 사용자 등록
    public User registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setName(registerRequest.getName());

        return userRepository.save(user);
    }

    // 사용자 정보 업데이트
    public User updateUser(String username, RegisterRequest updatedRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        user.setName(updatedRequest.getName());

        if (updatedRequest.getPassword() != null && !updatedRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedRequest.getPassword()));
        }

        return userRepository.save(user);
    }

    // 비밀번호 변경
    public void changePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }

    // 사용자 조회
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // 사용자 삭제
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    // 사용자 인증 확인
    public boolean isUserAuthenticated(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return user != null;
    }
}
