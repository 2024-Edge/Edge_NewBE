package com.hanium.edge.dto.power;

import com.hanium.edge.entity.LedStateEntity;
import com.hanium.edge.entity.DailyPowerDataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyPowerDataDTO {
    private List<LedStateEntity> ledStates; // 각 LED의 현재 상태 및 실시간 전력량
    private List<DailyPowerDataEntity> ledData; // 하루 동안의 LED별 전력 사용량
    private float totalPowerAllLeds; // 모든 LED의 하루 동안 총 전력 사용량
}
