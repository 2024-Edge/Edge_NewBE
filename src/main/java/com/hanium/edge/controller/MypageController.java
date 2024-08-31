package com.hanium.edge.controller;

import com.hanium.edge.dto.user.UpdateLevelDTO;
import com.hanium.edge.response.ResponseDTO;
import com.hanium.edge.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final LevelService levelService;

    @PostMapping("/target-power")
    public ResponseEntity<ResponseDTO<Void>> updateTargetPower(@RequestBody UpdateLevelDTO updateLevelDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        levelService.updateTargetPower(username, updateLevelDTO);
        return ResponseEntity.ok().build();

    }
}
