package com.harrybro.courseregistration.domain.university.controller;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.repository.AccountReposiitory;
import com.harrybro.courseregistration.domain.university.domain.Lecture;
import com.harrybro.courseregistration.domain.university.dto.DeleteLectureInBasketRequest;
import com.harrybro.courseregistration.domain.university.repository.BasketRepository;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import com.harrybro.courseregistration.global.config.auth.PrincipalDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class BasketController {

    private final AccountReposiitory accountReposiitory;
    private final BasketRepository basketRepository;
    private final LectureRepository lectureRepository;

    @Transactional(readOnly = true)
    @GetMapping("/basket")
    public String getBasket(Model model, @AuthenticationPrincipal PrincipalDetail principal)  {
        Account account = accountReposiitory.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        model.addAttribute("basket_lectures", account.getBasket().getLectures());
        model.addAttribute("enrollment_lectures", account.getEnrollment().getLectures());

        return "basket";
    }

    @Transactional
    @DeleteMapping("/basket")
    public String deleteLectureInBasket(DeleteLectureInBasketRequest dto, @AuthenticationPrincipal PrincipalDetail principal) {
        Account account = accountReposiitory.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        Lecture lecture = lectureRepository.findById(dto.getLectureID())
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));
        account.getBasket().deleteLecture(lecture);

        return "redirect:/basket";
    }

}
