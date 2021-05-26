package com.harrybro.courseregistration.domain.user.dto;

import com.harrybro.courseregistration.domain.university.domain.Basket;
import com.harrybro.courseregistration.domain.university.domain.Enrollment;
import com.harrybro.courseregistration.domain.user.domain.RoleType;
import com.harrybro.courseregistration.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @Email
    private String email;

    @Length(max = 30)
    private String password;

    public User toEntity(PasswordEncoder passwordEncoder, Basket basket, Enrollment enrollment) {
        return User.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .basket(basket)
                .enrollment(enrollment)
                .role(RoleType.ROLE_USER)
                .build();
    }

}
