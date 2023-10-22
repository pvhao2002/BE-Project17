package com.example.beproject17.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseModel {
    private String status;
    private String message;
    private Object data;

    public ResponseModel(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
