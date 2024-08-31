/*package com.hanium.edge.controller;

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
 */

package com.hanium.edge.controller;

import com.hanium.edge.dto.PowerDataDTO;
import com.hanium.edge.dto.DailyPowerDataDTO;
import com.hanium.edge.dto.MonthlyPowerSummaryDTO;
import com.hanium.edge.service.PowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/power")
@RequiredArgsConstructor
public class PowerController {

    private final PowerService powerService;

    @PostMapping("/data")
    public ResponseEntity<?> receivePowerData(@RequestBody PowerDataDTO powerData) {
        powerService.savePowerData(powerData);
        return ResponseEntity.ok("Data saved successfully");
    }

    @GetMapping("/daily")
    public ResponseEntity<DailyPowerDataDTO> getDailyPowerData(@RequestParam String date) {
        DailyPowerDataDTO dailyData = powerService.getDailyPowerData(date);
        return ResponseEntity.ok(dailyData);
    }

    @GetMapping("/monthly")
    public ResponseEntity<MonthlyPowerSummaryDTO> getMonthlyPowerData(@RequestParam String month) {
        MonthlyPowerSummaryDTO summary = powerService.getMonthlyPowerData(month);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/control")
    public ResponseEntity<?> controlLED(@RequestParam int ledNumber, @RequestParam String state) {
        powerService.controlLED(ledNumber, state);
        return ResponseEntity.ok("LED " + ledNumber + " is now " + state);
    }
}
