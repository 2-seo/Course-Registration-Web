package com.harrybro.courseregistration.domain.user.dto;

import com.harrybro.courseregistration.domain.user.domain.User;
import com.harrybro.courseregistration.domain.user.domain.RoleType;
import com.harrybro.courseregistration.domain.university.domain.Basket;
import com.harrybro.courseregistration.domain.university.domain.Enrollment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Getter
public class SignUpRequest {

    private String username;
    private String password;

    public User toEntity(BCryptPasswordEncoder passwordEncoder, Basket basket, Enrollment enrollment) {
        return User.builder()
                .username(this.username)
                .password(passwordEncoder.encode(this.password))
                .basket(basket)
                .enrollment(enrollment)
                .role(RoleType.ROLE_USER)
                .build();
    }

}
