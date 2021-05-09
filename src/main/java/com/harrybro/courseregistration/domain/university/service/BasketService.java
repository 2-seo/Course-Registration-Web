package com.harrybro.courseregistration.domain.university.service;

import com.harrybro.courseregistration.domain.user.domain.User;
import com.harrybro.courseregistration.domain.user.repository.UserRepository;
import com.harrybro.courseregistration.domain.university.domain.lecture.Lecture;
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
public class BasketService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public ResponseDto<?> saveLecture(Long lectureID, PrincipalDetail principal) {
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        Lecture lecture = lectureRepository.findById(lectureID)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));

        if (user.getBasket().getLectures().stream().anyMatch(l -> l == lecture))
            throw new IllegalArgumentException("이미 장바구니에 담긴 강의입니다.");

        user.getBasket().saveLecture(lecture);

        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("선택한 과목이 장바구니에 저장되었습니다.")
                .build();
    }

    @Transactional
    public ResponseDto<?> deleteLecture(Long lectureID, PrincipalDetail principal) {
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        Lecture lecture = lectureRepository.findById(lectureID)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));

        user.getBasket().deleteLecture(lecture);

        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("선택한 과목이 장바구니에서 삭제되었습니다.")
                .build();
    }

}
