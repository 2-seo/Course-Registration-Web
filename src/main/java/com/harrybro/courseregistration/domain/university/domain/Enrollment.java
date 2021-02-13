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
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.REFRESH)
//    private List<Account> accounts;

    @Setter
    @OneToOne
    private Account account;

    @OneToMany
    private List<Lecture> lectures;

    public void saveLecture(Lecture lecture) {
        this.lectures.add(lecture);
    }

    public void deleteLecture(Lecture lecture) {
        this.lectures.remove(lecture);
    }

}
