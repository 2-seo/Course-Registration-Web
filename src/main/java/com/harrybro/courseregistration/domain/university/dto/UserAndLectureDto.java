package com.harrybro.courseregistration.domain.university.dto;

import com.harrybro.courseregistration.domain.university.domain.lecture.Lecture;
import com.harrybro.courseregistration.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAndLectureDto {

    private User user;
    private Lecture lecture;

    public static UserAndLectureDto of(User user, Lecture lecture) {
        return new UserAndLectureDto(user, lecture);
    }

}
