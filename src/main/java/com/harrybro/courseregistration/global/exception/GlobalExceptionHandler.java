package com.harrybro.courseregistration.global.exception;

import com.harrybro.courseregistration.domain.user.exception.UsernameDuplicateException;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    /**
     * 이미 존재하는 username 인 경우 발생
     */
    @ExceptionHandler(value = UsernameDuplicateException.class)
    public ResponseEntity<ResponseDto<?>> handleRuntimeException(UsernameDuplicateException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<?>> handleRuntimeException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(e.getMessage())
                        .build());
    }

}
