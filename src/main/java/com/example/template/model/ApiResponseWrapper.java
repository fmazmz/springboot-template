package com.example.template.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "ApiResponse", description = "Standard API response wrapper")
public class ApiResponseWrapper<T> {

    @Schema(description = "Response payload data")
    private T data;

    @Schema(description = "HTTP status code", example = "200")
    private int statusCode;

    @Schema(description = "Unique operation identifier for tracking", example = "123e4567-e89b-12d3-a456-426614174000")
    private String operationId;

    @Schema(description = "Timestamp of the response", example = "2024-12-06T10:30:00")
    private LocalDateTime timestamp;

    // Success response helper
    public static <T> ApiResponseWrapper<T> success(T data) {
        return ApiResponseWrapper.<T>builder()
                .data(data)
                .statusCode(200)
                .operationId(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Created response helper
    public static <T> ApiResponseWrapper<T> created(T data) {
        return ApiResponseWrapper.<T>builder()
                .data(data)
                .statusCode(201)
                .operationId(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Error response helper
    public static <T> ApiResponseWrapper<T> error(int statusCode) {
        return ApiResponseWrapper.<T>builder()
                .data(null)
                .statusCode(statusCode)
                .operationId(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // No content response helper
    public static <T> ApiResponseWrapper<T> noContent() {
        return ApiResponseWrapper.<T>builder()
                .data(null)
                .statusCode(204)
                .operationId(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now())
                .build();
    }
}