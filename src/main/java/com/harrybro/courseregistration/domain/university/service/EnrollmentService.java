package com.harrybro.courseregistration.domain.university.service;

import com.harrybro.courseregistration.domain.university.domain.Lecture;
import com.harrybro.courseregistration.domain.university.dto.EnrollmentCancelResponse;
import com.harrybro.courseregistration.domain.university.dto.EnrollmentSaveResponse;
import com.harrybro.courseregistration.domain.university.dto.UserAndLectureDto;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import com.harrybro.courseregistration.domain.university.util.UserAndLectureValidator;
import com.harrybro.courseregistration.domain.user.domain.User;
import com.harrybro.courseregistration.domain.user.repository.UserRepository;
import com.harrybro.courseregistration.global.config.auth.PrincipalDetail;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EnrollmentService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final UserAndLectureValidator userAndLectureValidator;

    @Transactional
    public ResponseDto<EnrollmentSaveResponse> save(Long lectureId, PrincipalDetail principal) {
        UserAndLectureDto userAndLectureDto = userAndLectureValidator.validate(principal, lectureId);
        User user = userAndLectureDto.getUser();
        Lecture lecture = userAndLectureDto.getLecture();
        user.enroll(lecture);
        return ResponseDto.of("선택한 과목을 수강신청 완료하였습니다.", EnrollmentSaveResponse.of(user, lecture));
    }

    @Transactional
    public ResponseDto<EnrollmentCancelResponse> delete(Long lectureId, PrincipalDetail principal) {
        UserAndLectureDto userAndLectureDto = userAndLectureValidator.validate(principal, lectureId);
        User user = userAndLectureDto.getUser();
        Lecture lecture = userAndLectureDto.getLecture();
        user.cancelEnrollment(lecture);
        return ResponseDto.of("선택한 과목을 수강 취소했습니다.", EnrollmentCancelResponse.from(user));
    }

}
