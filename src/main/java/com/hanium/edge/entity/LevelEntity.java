package com.hanium.edge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(columnDefinition = "double default 0")
    private double targetPower;   // 목표 전력량

    @Column(columnDefinition = "double default 0")
    private double actualPower;   // 실제 전력량

    @Column(columnDefinition = "int default 1")
    private int sproutLevel;       // 새싹 레벨

    private LocalDate updatedAt; // 업데이트 날짜
}
