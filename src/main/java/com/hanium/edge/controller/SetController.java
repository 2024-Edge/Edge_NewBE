package com.hanium.edge.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hanium.edge.service.BrightnessService;
import com.hanium.edge.service.HumidityService;
import com.hanium.edge.service.DustService;

@AllArgsConstructor
@Controller
public class SetController {

    // private final BrightnessService brightnessService;
    private final HumidityService humidityService;
    private final DustService dustService;

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("temperature", humidityService.getTemperature());
        model.addAttribute("humidity", humidityService.getHumidity());
        model.addAttribute("dustDensity", dustService.getDustDensity());
        return "test";
    }

}
