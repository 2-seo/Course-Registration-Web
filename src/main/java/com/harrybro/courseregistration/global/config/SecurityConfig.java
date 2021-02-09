package com.harrybro.courseregistration.global.config;

import com.harrybro.courseregistration.global.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
@EnableWebSecurity // 시큐리티 필터를 등록한다. (이곳에서)
@Configuration // bean 등록 : spring container 에서 객체를 관리할 수 있게 하는 것
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/auth/**", "/assets/**") // auth 로 시작하는 거 전부
                    .permitAll() // 허용하고,
                    .antMatchers("/board/saveForm'").hasAnyRole("USER", "ADMIN")
                    .anyRequest() // 그 외 request 는
                    .authenticated() // 인증이 필요하다.
                .and()
                    .formLogin()
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth/login") // spring security 가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
                    .defaultSuccessUrl("/");
    }

}