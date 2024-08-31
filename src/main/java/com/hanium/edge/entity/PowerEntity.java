package com.hanium.edge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String powerName;  // 장치 이름 (에어컨, 가습기, 공기청정기, 형광등)
    private boolean status;    // 장치의 on/off 상태

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void updateStatus(boolean status) {
        this.status = status;
    }
}
