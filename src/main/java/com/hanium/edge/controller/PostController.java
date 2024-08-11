package com.hanium.edge.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hanium.edge.service.HumidityService;
import com.hanium.edge.service.BrightnessService;

@Controller
@AllArgsConstructor
public class PostController {

    private final HumidityService humidityService;
    private final BrightnessService brightnessService;

    @PostMapping("/data")
    public String receiveData(@RequestParam("temperature") float temperature, @RequestParam("humidity") float humidity, @RequestParam("brightness") float brightness, Model model) {
        humidityService.setTemperature(temperature);
        humidityService.setHumidity(humidity);
        brightnessService.setBrightness(brightness);
        model.addAttribute("temperature", temperature);
        model.addAttribute("humidity", humidity);
        model.addAttribute("brightness", brightness);
        return "redirect:/test";
    }
}