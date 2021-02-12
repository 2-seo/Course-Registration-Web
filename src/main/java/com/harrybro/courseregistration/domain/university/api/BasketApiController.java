package com.harrybro.courseregistration.domain.university.api;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.repository.AccountReposiitory;
import com.harrybro.courseregistration.domain.university.domain.Lecture;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import com.harrybro.courseregistration.global.config.auth.PrincipalDetail;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class BasketApiController {

    private final AccountReposiitory accountReposiitory;
    private final LectureRepository lectureRepository;

    @Transactional
    @DeleteMapping("/basket/{id}")
    public ResponseDto deleteLectureInBasket(@PathVariable("id") Long lectureID, @AuthenticationPrincipal PrincipalDetail principal) {
        Account account = accountReposiitory.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        Lecture lecture = lectureRepository.findById(lectureID)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));
        account.getBasket().deleteLecture(lecture);

        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("선택한 과목이 장바구니에서 삭제되었습니다.")
                .build();
    }

}
