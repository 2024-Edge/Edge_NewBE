package com.hanium.edge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LedStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float currentPower; // 현재 사용 중인 전력량
    private String state; // ON 또는 OFF 상태
    private String lastUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
