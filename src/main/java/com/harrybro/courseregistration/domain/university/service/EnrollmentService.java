package com.harrybro.courseregistration.domain.university.service;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.repository.AccountReposiitory;
import com.harrybro.courseregistration.domain.university.domain.lecture.Lecture;
import com.harrybro.courseregistration.domain.university.dto.EnrollmentCancelResponse;
import com.harrybro.courseregistration.domain.university.dto.EnrollmentSaveResponse;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import com.harrybro.courseregistration.global.config.auth.PrincipalDetail;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EnrollmentService {

    private final AccountReposiitory accountReposiitory;
    private final LectureRepository lectureRepository;

    @Transactional
    public ResponseDto<EnrollmentSaveResponse> save(Long lectureID, PrincipalDetail principal) {
        Account account = accountReposiitory.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        Lecture lecture = lectureRepository.findById(lectureID)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));

        account.enroll(lecture);

        return ResponseDto.<EnrollmentSaveResponse>builder()
                .status(HttpStatus.OK)
                .message("선택한 과목을 수강신청 완료하였습니다.")
                .data(new EnrollmentSaveResponse(lecture, account))
                .build();
    }

    @Transactional
    public ResponseDto delete(Long lectureID, PrincipalDetail principal) {
        Account account = accountReposiitory.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        Lecture lecture = lectureRepository.findById(lectureID)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));

        account.cancelEnrollment(lecture);

        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("선택한 과목을 수강 취소하였습니다.")
                .data(new EnrollmentCancelResponse(account))
                .build();
    }

}
