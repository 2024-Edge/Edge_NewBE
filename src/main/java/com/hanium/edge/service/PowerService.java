package com.hanium.edge.service;

import com.hanium.edge.dto.power.PowerDTO;
import com.hanium.edge.entity.PowerEntity;
import com.hanium.edge.entity.UserEntity;
import com.hanium.edge.exception.UserNotFoundException;
import com.hanium.edge.repository.PowerRepository;
import com.hanium.edge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PowerService {

    private final PowerRepository powerRepository;
    private final UserRepository userRepository;
    private final String ESP32_URL = "http://172.20.10.10";  // ESP32의 IP 주소로 변경

    public String controlPower(String username, Long powerId, String state) {
        // 사용자 조회
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // 전력 장치 조회
        Optional<PowerEntity> optionalPower = powerRepository.findById(powerId);
        if (optionalPower.isEmpty()) {
            return "Power not found";
        }

        PowerEntity powerEntity = optionalPower.get();

        // 사용자가 해당 전력 장치를 제어할 권한이 있는지 확인
        if (!powerEntity.getUser().getUsername().equals(username)) {
            return "Unauthorized access";
        }

        // ESP32에 제어 신호 보내기
        String result = sendControlSignal(powerId, state);
        if (result.equals("success")) {
            // 전력 장치 상태 업데이트
            boolean status = "on".equalsIgnoreCase(state);
            powerEntity.updateStatus(status);
            powerRepository.save(powerEntity);
            return "success";
        } else {
            return "Failed to control power";
        }
    }

    private String sendControlSignal(Long power, String state) {
        try {
            // ESP32에 요청을 보낼 URL을 생성합니다.
            String url = ESP32_URL + "/power?power=" + power + "&state=" + state;
            RestTemplate restTemplate = new RestTemplate();

            // ESP32로 GET 요청을 보내고, 응답을 받습니다.
            String response = restTemplate.getForObject(url, String.class);

            // 응답 메시지에 "success"가 포함되어 있는지 확인합니다.
            return response.contains("success") ? "success" : "error";
        } catch (Exception e) {
            // 예외가 발생한 경우 "error"를 반환합니다.
            return "error";
        }
    }

    // 전력 장치 상태 조회 메소드 추가
    public List<PowerDTO> getAllPowerEntitiesForUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        List<PowerEntity> powerEntities = powerRepository.findByUser(user);
        return powerEntities.stream()
                .map(power -> new PowerDTO(power.getId(), power.getPowerName(), power.isStatus()))
                .collect(Collectors.toList());
    }
}
