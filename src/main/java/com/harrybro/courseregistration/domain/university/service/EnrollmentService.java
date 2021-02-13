package com.harrybro.courseregistration.domain.university.service;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.repository.AccountReposiitory;
import com.harrybro.courseregistration.domain.university.domain.Lecture;
import com.harrybro.courseregistration.domain.university.dto.EnrollmentSaveResponse;
import com.harrybro.courseregistration.domain.university.repository.EnrollmentRepository;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import com.harrybro.courseregistration.global.config.auth.PrincipalDetail;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EnrollmentService {

    private final AccountReposiitory accountReposiitory;
    private final LectureRepository lectureRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public ResponseDto<EnrollmentSaveResponse> save(Long lectureID, PrincipalDetail principal) {
        Account account = accountReposiitory.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        Lecture lecture = lectureRepository.findById(lectureID)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));

        List<Lecture> lectures = account.getEnrollment().getLectures();
        for (Lecture l : lectures) {
            if (l.equals(lecture)) {
                throw new IllegalArgumentException("이미 수강 신청된 강의입니다.");
            }
        }

        account.getEnrollment().saveLecture(lecture);

        return ResponseDto.<EnrollmentSaveResponse>builder()
                .status(HttpStatus.OK)
                .message("선택한 과목이 수강 신청 완료되었습니다.")
                .data(new EnrollmentSaveResponse(lecture))
                .build();
    }

    @Transactional
    public ResponseDto delete(Long lectureID, PrincipalDetail principal) {
        Account account = accountReposiitory.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        Lecture lecture = lectureRepository.findById(lectureID)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));

        account.getEnrollment().deleteLecture(lecture);

        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("선택한 과목이 장바구니에서 삭제되었습니다.")
                .build();
    }

}
