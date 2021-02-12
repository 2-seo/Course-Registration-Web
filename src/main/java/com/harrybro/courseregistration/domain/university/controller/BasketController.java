package com.harrybro.courseregistration.domain.university.controller;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.repository.AccountReposiitory;
import com.harrybro.courseregistration.domain.university.repository.BasketRepository;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import com.harrybro.courseregistration.global.config.auth.PrincipalDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BasketController {

    private final AccountReposiitory accountReposiitory;

    @Transactional(readOnly = true)
    @GetMapping("/basket")
    public String getBasket(Model model, @AuthenticationPrincipal PrincipalDetail principal)  {
        Account account = accountReposiitory.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
        model.addAttribute("basket_lectures", account.getBasket().getLectures());
        model.addAttribute("enrollment_lectures", account.getEnrollment().getLectures());

        return "basket";
    }


}
