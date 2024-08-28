package com.hanium.edge.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PowerService {

    private final String ESP32_URL = "http://172.20.10.10";  // ESP32의 IP 주소로 변경

    public String sendControlSignal(int power, String state) {
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
}

