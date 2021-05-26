package com.harrybro.courseregistration.domain.university.mail.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailAuthCodeResponse {

    private String email;
    private String message;

}