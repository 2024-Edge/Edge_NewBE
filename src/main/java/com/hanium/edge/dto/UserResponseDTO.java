package com.hanium.edge.dto;

import com.hanium.edge.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    // 닉네임
    private String username;

    public static UserResponseDTO toDto(UserEntity entity) {
        return UserResponseDTO.builder()
                .username(entity.getUsername())
                .build();
    }
}