package com.hanium.edge.controller;

import com.hanium.edge.code.SuccessCode;
import com.hanium.edge.dto.user.LevelDTO;
import com.hanium.edge.dto.user.UpdateLevelDTO;
import com.hanium.edge.response.ResponseDTO;
import com.hanium.edge.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/level")
public class LevelController {

    private final LevelService levelService;

    @GetMapping
    public ResponseEntity<ResponseDTO<LevelDTO>> getLevel() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LevelDTO levelDTO = levelService.getLevel(username);
        System.out.println("Username from security context: " + username);
        ResponseDTO<LevelDTO> response = new ResponseDTO<>(
                SuccessCode.SUCCESS_RETRIEVE_USER,
                levelDTO
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/target")
    public ResponseEntity<ResponseDTO<Void>> updateTargetPower(@RequestBody UpdateLevelDTO updateLevelDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        levelService.updateTargetPower(username, updateLevelDTO);
        ResponseDTO<Void> response = new ResponseDTO<>(
                SuccessCode.SUCCESS_UPDATE,  // SuccessCode 객체 사용
                null
        );

        return ResponseEntity.ok(response);
    }

//    @PostMapping("/actual")
//    public ResponseEntity<ResponseDTO<Void>> updateActualPower(@RequestParam double actualPower) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        levelService.updateActualPower(username, actualPower);
//        return ResponseEntity.ok(new ResponseDTO<>(null));
//    }
}
