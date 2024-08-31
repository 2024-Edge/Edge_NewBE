package com.hanium.edge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private boolean success;
    private Integer status;
    private String message;
    private T data;

    // 기본 생성자 추가
    public ResponseDTO(T data) {
        this.success = true;
        this.status = 200;
        this.message = "Success";
        this.data = data;
    }

    // 상태 코드와 메시지를 받는 생성자
    public ResponseDTO(Integer status, String message, T data) {
        this.success = (status == 200);
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
