package com.harrybro.courseregistration.global.error;

import com.harrybro.courseregistration.domain.account.exception.UsernameDuplicateException;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
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
    public ResponseDto<Object> handleRuntimeException(UsernameDuplicateException e) {
        return ResponseDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(e.getMessage())
                .build();
    }

}
