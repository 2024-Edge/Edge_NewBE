package com.hanium.edge.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    // 닉네임
    private String username;
    // 비밀번호
    private String password;
}