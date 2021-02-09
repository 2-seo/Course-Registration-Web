package com.harrybro.courseregistration.domain.account.dto;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.domain.RoleType;
import com.harrybro.courseregistration.domain.university.domain.Basket;
import com.harrybro.courseregistration.domain.university.domain.Enrollment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Getter
public class CreateAccountRequest {

    private String username;
    private String password;

    public Account toEntity(BCryptPasswordEncoder passwordEncoder, Basket basket, Enrollment enrollment) {
        return Account.builder()
                .username(this.username)
                .password(passwordEncoder.encode(this.password))
                .basket(basket)
                .enrollment(enrollment)
                .role(RoleType.ROLE_USER)
                .build();
    }

}
