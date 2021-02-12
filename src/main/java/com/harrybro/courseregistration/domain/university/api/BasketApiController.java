package com.harrybro.courseregistration.domain.university.api;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.repository.AccountReposiitory;
import com.harrybro.courseregistration.domain.university.domain.Lecture;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import com.harrybro.courseregistration.domain.university.service.BasketService;
import com.harrybro.courseregistration.global.config.auth.PrincipalDetail;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class BasketApiController {

    private final BasketService basketService;

    @Transactional
    @DeleteMapping("/basket/{id}")
    public ResponseDto deleteLectureInBasket(@PathVariable("id") Long lectureID, @AuthenticationPrincipal PrincipalDetail principal) {
        return basketService.deleteLectureInBasket(lectureID, principal);
    }

}
