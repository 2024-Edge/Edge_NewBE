package com.hanium.edge.controller;

import com.hanium.edge.dto.ChatRequestDTO;
import com.hanium.edge.dto.ChatResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
public class ChatController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt") String prompt) {
        ChatRequestDTO request = new ChatRequestDTO(model, prompt);

        ChatResponseDTO chatGPTResponse = template.postForObject(apiURL, request, ChatResponseDTO.class);

        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }
}
