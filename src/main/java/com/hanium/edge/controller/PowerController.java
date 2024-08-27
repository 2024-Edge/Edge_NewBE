package com.hanium.edge.controller;

import com.hanium.edge.service.PowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/power")
public class PowerController {

    private final PowerService powerService;

    // 생성자를 통해 PowerService를 주입합니다.
    public PowerController(PowerService powerService) {
        this.powerService = powerService;
    }

    // GET 요청을 받아서 특정 Power (전력 장치)를 제어합니다.
    @GetMapping("/control")
    public ResponseEntity<String> controlPower(@RequestParam int power, @RequestParam String state) {
        String result = powerService.sendControlSignal(power, state);

        // 제어 성공 여부에 따라 적절한 응답을 반환합니다.
        if (result.equals("success")) {
            return ResponseEntity.ok("Succes: Power " + power + " turned " + state);
        } else {
            return ResponseEntity.status(500).body("Failed to control power");
        }
    }
}
