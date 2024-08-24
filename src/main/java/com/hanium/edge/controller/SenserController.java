/*
package com.hanium.edge.controller;

import com.hanium.edge.service.DustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SenserController {

    @Autowired
    private DustService dustService;

    @GetMapping("/dust")
    public Map<String, Object> getDustDensity() {
        Map<String, Object> response = new HashMap<>();
        response.put("dustDensity", dustService.getDustDensity());
        return response;
    }

    @PostMapping("/dust")
    public void updateDustDensity(@RequestParam float dustDensity) {
        dustService.updateDustDensity(dustDensity);
    }
}
*/