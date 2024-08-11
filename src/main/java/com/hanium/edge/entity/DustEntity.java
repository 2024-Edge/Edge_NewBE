package com.hanium.edge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DustEntity {
    @Id
    private Long id = 1L;
    private float dust;

    public DustEntity(float dust) {
        this.dust = dust;
    }
}
