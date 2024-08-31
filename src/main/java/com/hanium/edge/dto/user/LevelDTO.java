package com.hanium.edge.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelDTO {
    private double targetPower;
    private double actualPower;
    private int sproutLevel;
}
