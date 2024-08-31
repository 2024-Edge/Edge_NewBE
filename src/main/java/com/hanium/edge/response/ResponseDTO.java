package com.hanium.edge.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private boolean success;
    private Integer status;
    private String message;
    private T data;

    // 생성자: 상태 코드, 메시지, 데이터를 인자로 받음
    public ResponseDTO(Integer status, String message, T data) {
        this.success = (status == 200); // 성공 여부를 상태 코드로 결정
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
