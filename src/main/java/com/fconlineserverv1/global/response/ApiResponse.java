package com.fconlineserverv1.global.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"code", "message", "data"})
public class ApiResponse<T> {
    private final String code;
    private final String message;
    private T data;

    public static <T> ApiResponse<T> onSuccess(String status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }
    public static <T> ApiResponse<T> onFailure(String status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

}
