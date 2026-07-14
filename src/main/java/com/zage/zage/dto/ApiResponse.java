package com.zage.zage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;

    private int code;

    private String message;

    private T data;

    private LocalDateTime timestamp;


    public static <T> ApiResponse<T> success(
            int code,
            String message,
            T data
    ) {
        return new ApiResponse<>(
                "SUCCESS",
                code,
                message,
                data,
                LocalDateTime.now()
        );
    }


    public static <T> ApiResponse<T> error(
            int code,
            String message
    ) {
        return new ApiResponse<>(
                "ERROR",
                code,
                message,
                null,
                LocalDateTime.now()
        );
    }
}