package com.hanium.edge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;  // 사용자 아이디
    private String password;  // 비밀번호
    private String name;      // 사용자 이름
}
