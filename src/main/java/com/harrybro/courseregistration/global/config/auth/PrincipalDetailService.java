package com.harrybro.courseregistration.global.config.auth;

import com.harrybro.courseregistration.domain.account.domain.Account;
import com.harrybro.courseregistration.domain.account.repository.AccountReposiitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final AccountReposiitory accountReposiitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account principal = accountReposiitory.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new PrincipalDetail(principal);
    }
}
