package com.harrybro.courseregistration.domain.university.dto;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.university.domain.lecture.Lecture;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class EnrollmentSaveResponse {

    private Long id;
    private String name;
    private String lecturer;
    private int credit;
    private String major;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;

    private int leftCredit;

    public EnrollmentSaveResponse(Lecture lecture, Account account) {
        this.id = lecture.getId();
        this.name = lecture.getName();
        this.lecturer = lecture.getLecturer();
        this.credit = lecture.getCredit();
        this.major = lecture.getMajor().getName();
        this.day = lecture.getDay().getValue();
        this.startTime = lecture.getPeriod().getStartTime();
        this.endTime = lecture.getPeriod().getEndTime();

        this.leftCredit = account.getCredit();
    }

}
