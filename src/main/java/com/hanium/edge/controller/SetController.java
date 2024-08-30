package com.hanium.edge.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hanium.edge.service.BrightnessService;
import com.hanium.edge.service.HumidityService;
import com.hanium.edge.service.DustService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class SetController {

    private final BrightnessService brightnessService;
    private final HumidityService humidityService;
    private final DustService dustService;

    @GetMapping
    public ResponseEntity<Map<String, Float>> test() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Float> data = new HashMap<>();
        try {
            data.put("temperature", humidityService.getTemperature(username));
            data.put("humidity", humidityService.getHumidity(username));
            data.put("dustDensity", dustService.getDustDensity(username));
            data.put("brightness", brightnessService.getBrightness(username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(data);
    }
}
