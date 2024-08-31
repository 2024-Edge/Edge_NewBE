package com.hanium.edge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LedPowerDTO {
    private String ledName;
    private float currentPower;
    private float totalPower;
    private LocalDate date;
}
