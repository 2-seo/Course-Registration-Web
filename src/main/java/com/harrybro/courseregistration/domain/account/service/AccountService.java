package com.harrybro.courseregistration.domain.account.service;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.dto.CreateAccountRequest;
import com.harrybro.courseregistration.domain.account.exception.UsernameDuplicateException;
import com.harrybro.courseregistration.domain.account.repository.AccountReposiitory;
import com.harrybro.courseregistration.domain.university.domain.Basket;
import com.harrybro.courseregistration.domain.university.domain.Enrollment;
import com.harrybro.courseregistration.domain.university.repository.BasketRepository;
import com.harrybro.courseregistration.domain.university.repository.EnrollmentRepository;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountReposiitory accountReposiitory;
    private final BasketRepository basketRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public ResponseDto<Object> createAccount(CreateAccountRequest dto) {

        if (accountReposiitory.existsByUsername(dto.getUsername())) {
            throw new UsernameDuplicateException(dto.getUsername());
        }

        Basket basket = basketRepository.save(new Basket());
        Enrollment enrollment = enrollmentRepository.save(new Enrollment());
        Account account = accountReposiitory.save(dto.toEntity(passwordEncoder, basket, enrollment));
        basket.setAccount(account);
        enrollment.setAccount(account);

        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("회원가입이 완료되었습니다.")
                .build();

    }

}
