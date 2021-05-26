package com.harrybro.courseregistration.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionMessage {

    LACK_OF_CREDIT("수강 가능 학점이 부족합니다");

    private final String message;

}
