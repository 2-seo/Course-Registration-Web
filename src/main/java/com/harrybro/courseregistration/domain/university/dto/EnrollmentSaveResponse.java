package com.harrybro.courseregistration.domain.university.dto;

import com.harrybro.courseregistration.domain.university.domain.Lecture;
import lombok.Getter;

@Getter
public class EnrollmentSaveResponse {

    private Long id;
    private String name;
    private String lecturer;
    private int credit;
    private String time;
    private String major;

    public EnrollmentSaveResponse(Lecture lecture) {
        this.id = lecture.getId();
        this.name = lecture.getName();
        this.lecturer = lecture.getLecturer();
        this.credit = lecture.getCredit();
        this.time = lecture.getTime();
        this.major = lecture.getMajor().getName();
    }
}
