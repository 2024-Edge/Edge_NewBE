package com.hanium.edge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hanium.edge.service.BrightnessService;
import com.hanium.edge.service.HumidityService;

@Controller
public class SetController {

    private final BrightnessService brightnessService;
    private final HumidityService humidityService;

    @Autowired
    public SetController(BrightnessService brightnessService, HumidityService humidityService) {
        this.brightnessService = brightnessService;
        this.humidityService = humidityService;
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("brightness", brightnessService.getBrightness());

        model.addAttribute("temperature", humidityService.getTemperature());
        model.addAttribute("humidity", humidityService.getHumidity());

        return "test";
    }
}
