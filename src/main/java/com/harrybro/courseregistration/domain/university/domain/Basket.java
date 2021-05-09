package com.harrybro.courseregistration.domain.university.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.harrybro.courseregistration.domain.user.domain.User;
import com.harrybro.courseregistration.domain.university.domain.lecture.Lecture;
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
    private User user;

    @JsonIgnoreProperties({"major"})
    @OrderBy("id desc ")
    @OneToMany
    private List<Lecture> lectures;

    public void saveLecture(Lecture lecture) {
        this.lectures.add(lecture);
    }

    public void deleteLecture(Lecture lecture) {
        this.lectures.remove(lecture);
    }

}
