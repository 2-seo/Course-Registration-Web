package com.harrybro.courseregistration.domain.account.repository;

import com.harrybro.courseregistration.domain.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountReposiitory extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);
}
