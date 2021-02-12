package com.harrybro.courseregistration.domain.university.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"major"})
    @OrderBy("id desc ")
    @OneToMany
    private List<Lecture> lectures;

    public void deleteLecture(Lecture lecture) {
        lectures.remove(lecture);
    }

}
