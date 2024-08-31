package com.hanium.edge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LedPowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ledName;
    private float currentPower;  // 실시간 전력 사용량
    private float totalPower;    // 해당 날짜의 누적 전력 사용량
    private LocalDate date;      // 전력 사용량이 기록된 날짜

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public void updatePower(float power) {
        this.currentPower = power;
        this.totalPower += power;
    }
}
