package com.harrybro.courseregistration.domain.user.service;

import com.harrybro.courseregistration.domain.user.domain.User;
import com.harrybro.courseregistration.domain.user.dto.SignUpRequest;
import com.harrybro.courseregistration.domain.user.exception.UsernameDuplicateException;
import com.harrybro.courseregistration.domain.user.repository.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public ResponseDto<?> create(SignUpRequest dto) {
        if (userRepository.existsByUsername(dto.getUsername())) throw new UsernameDuplicateException(dto.getUsername());

        Basket basket = basketRepository.save(new Basket());
        Enrollment enrollment = enrollmentRepository.save(new Enrollment());
        User user = userRepository.save(dto.toEntity(passwordEncoder, basket, enrollment));
        return ResponseDto.builder()
                .status(HttpStatus.OK)
                .message("회원가입이 완료되었습니다.")
                .build();
    }

}
