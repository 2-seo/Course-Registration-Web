package com.harrybro.courseregistration.domain.university.domain;

import com.harrybro.courseregistration.domain.account.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "basket", cascade = CascadeType.REMOVE)
//    private List<Account> accounts;

    @Setter
    @OneToOne
    private Account account;

    @OneToMany
    private List<Lecture> lectures;


}
