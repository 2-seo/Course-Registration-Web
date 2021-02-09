package com.harrybro.courseregistration.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ResponseDto<T> {
    HttpStatus status;
    int statusCode;
    String message;
    String errorMessage;
    LocalDateTime responseTime = LocalDateTime.now();
    T data;

    @Builder
    public ResponseDto(HttpStatus status, String message, String errorMessage, T data) {
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.errorMessage = errorMessage;
        this.data = data;
    }
}
