package com.harrybro.courseregistration.domain.university.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lecturer;

    private int credit;

    private String time;

    @ManyToOne
    private Major major;

    @Builder
    public Lecture(String name, String lecturer, int credit, String time, Major major) {
        this.name = name;
        this.lecturer = lecturer;
        this.credit = credit;
        this.time = time;
        this.major = major;
    }

}
