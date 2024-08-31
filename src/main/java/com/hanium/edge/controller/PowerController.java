package com.hanium.edge.controller;

import com.hanium.edge.dto.power.PowerDTO;
import com.hanium.edge.entity.PowerEntity;
import com.hanium.edge.service.PowerControlService;
import com.hanium.edge.service.PowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/power")
@AllArgsConstructor
public class PowerController {

    private final PowerService powerService;
    private final PowerControlService powerControlService;

    // GET 요청을 받아서 특정 Power (전력 장치)를 제어합니다.
    @GetMapping("/control")
    public ResponseEntity<String> controlPower(@RequestParam Long powerId, @RequestParam String state) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String result = powerService.controlPower(username, powerId, state);

        if (result.equals("success")) {
            return ResponseEntity.ok("Success: Power " + powerId + " turned " + state);
        } else {
            return ResponseEntity.status(500).body(result);
        }
    }

    // GET 요청을 받아서 모든 전력 장치 상태를 조회합니다.
    @GetMapping("/status")
    public ResponseEntity<List<PowerDTO>> getAllPowerStatus() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<PowerDTO> powerDTOs = powerService.getAllPowerEntitiesForUser(username);
        return ResponseEntity.ok(powerDTOs);
    }
}
