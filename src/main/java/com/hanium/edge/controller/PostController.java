package com.hanium.edge.controller;

import com.hanium.edge.dto.response.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hanium.edge.service.HumidityService;
import com.hanium.edge.service.BrightnessService;
import com.hanium.edge.service.DustService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PostController {

    private final HumidityService humidityService;
    private final BrightnessService brightnessService;
    private final DustService dustService;

    @PostMapping("/data")
    public ResponseEntity<ResponseDTO> receiveData(@RequestParam("temperature") float temperature, @RequestParam("humidity") float humidity, @RequestParam("dustDensity") float dustDensity, @RequestParam("brightness") float brightness) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        humidityService.setTemperature(username, temperature);
        humidityService.setHumidity(username, humidity);
        dustService.setDustDensity(username, dustDensity);
        brightnessService.setBrightness(username, brightness);

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(new ResponseDTO(200, "센서 데이터 저장 완료", null));
    }
}
