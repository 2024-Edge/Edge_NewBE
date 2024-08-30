package com.hanium.edge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class BrightnessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float brightness;

    @ManyToOne // 다대일
    @JoinColumn(name = "user_id", nullable = false) // 외래 키
    private UserEntity user;
}