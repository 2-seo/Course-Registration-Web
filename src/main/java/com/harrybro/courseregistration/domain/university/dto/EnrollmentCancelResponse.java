package com.harrybro.courseregistration.domain.university.dto;

import com.harrybro.courseregistration.domain.user.domain.User;
import lombok.Getter;

@Getter
public class EnrollmentCancelResponse {

    private int leftCredit;

    public EnrollmentCancelResponse(User user) {
        this.leftCredit = user.getCredit();
    }

}
