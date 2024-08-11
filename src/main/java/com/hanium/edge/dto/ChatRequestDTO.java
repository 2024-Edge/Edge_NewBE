package com.hanium.edge.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRequestDTO {
    private String model;
    private List<ChatDTO> messages;

    public ChatRequestDTO(String model, String prompt) {
        this.model = model;
        this.messages =  new ArrayList<>();
        this.messages.add(new ChatDTO("user", prompt));
    }
}