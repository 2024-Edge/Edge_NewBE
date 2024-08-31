package com.hanium.edge.dto.power;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PowerDTO {

    private Long id;
    private String powerName;
    private boolean status;

    // 생성자, getter, setter
}
