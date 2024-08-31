package com.hanium.edge.controller;

import com.hanium.edge.dto.LedPowerDTO;
import com.hanium.edge.response.ResponseDTO;
import com.hanium.edge.service.LedPowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/led")
public class LedPowerController {

    private final LedPowerService ledPowerService;

    @GetMapping("/daily")
    public ResponseEntity<ResponseDTO<List<LedPowerDTO>>> getDailyLedPower(@RequestParam LocalDate date) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<LedPowerDTO> powerData = ledPowerService.getDailyLedPower(username, date);
        return ResponseEntity.ok(new ResponseDTO<>(200, "Daily LED power retrieved successfully", powerData));
    }
}

