package com.hanium.edge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyPowerDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private int ledId;
    private float dailyTotalPower;  // 하루 동안의 총 전력 사용량

    public void addToDailyTotalPower(float power) {
        this.dailyTotalPower += power;
    }
}
