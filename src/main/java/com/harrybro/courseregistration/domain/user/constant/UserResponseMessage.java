package com.harrybro.courseregistration.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserResponseMessage {

    SiGN_UP_SUCCESS("회원가입이 완료되었습니다.");

    private final String message;

}
