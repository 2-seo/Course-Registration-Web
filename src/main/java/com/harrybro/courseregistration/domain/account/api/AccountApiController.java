package com.harrybro.courseregistration.domain.account.api;

import com.harrybro.courseregistration.domain.account.dto.CreateAccountRequest;
import com.harrybro.courseregistration.domain.account.service.AccountService;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountApiController {

    private final AccountService accountService;

    @PostMapping("/auth/create-account")
    public ResponseDto<Object> create(@RequestBody CreateAccountRequest dto) {
        return accountService.createAccount(dto);
    }

}
