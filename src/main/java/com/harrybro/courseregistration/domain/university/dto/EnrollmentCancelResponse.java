package com.harrybro.courseregistration.domain.university.dto;

import com.harrybro.courseregistration.domain.account.domain.Account;
import lombok.Getter;

@Getter
public class EnrollmentCancelResponse {

    private int leftCredit;

    public EnrollmentCancelResponse(Account account) {
        this.leftCredit = account.getCredit();
    }

}
