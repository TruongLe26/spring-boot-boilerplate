package com.truonglq.demo.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardApiResponse<T> {
    boolean success;
    ErrorResponse error;
    T result;
    LocalDateTime timestamp = LocalDateTime.now();
    String message;

    public static <T> StandardApiResponse<T> success (T result, String message) {
        return StandardApiResponse.<T>builder()
                .success(true)
                .result(result)
                .message(message)
                .build();
    }

    public static <T> StandardApiResponse<T> success (String message) {
        return StandardApiResponse.<T>builder()
                .success(true)
                .message(message)
                .build();
    }

    public static <T> StandardApiResponse<T> error (int code, String message) {
        return StandardApiResponse.<T>builder()
                .success(false)
                .error(new ErrorResponse(code, message))
                .build();
    }

    @Data
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ErrorResponse {
        int code;
        String info;
    }
}
