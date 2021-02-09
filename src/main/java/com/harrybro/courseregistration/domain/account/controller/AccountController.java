package com.harrybro.courseregistration.domain.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/auth/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/auth/create-account")
    public String createAccountForm() {
        return "create_account";
    }

}
