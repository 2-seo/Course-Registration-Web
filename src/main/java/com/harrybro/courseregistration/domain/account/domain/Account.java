package com.harrybro.courseregistration.domain.account.domain;

import com.harrybro.courseregistration.domain.university.domain.Basket;
import com.harrybro.courseregistration.domain.university.domain.Enrollment;
import com.harrybro.courseregistration.domain.university.domain.lecture.Lecture;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false)
    private String password;

    private int credit;

    @OneToOne
    private Basket basket;

    @OneToOne
    private Enrollment enrollment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    @Builder
    public Account(String username, String password, Basket basket, Enrollment enrollment, RoleType role) {
        this.username = username;
        this.password = password;
        this.basket = basket;
        this.enrollment = enrollment;
        this.role = role;
    }

    public void enroll(Lecture lecture) {
        if (this.credit >= lecture.getCredit()) {
            boolean result = this.enrollment.saveLecture(lecture);
            if (result) {
                this.credit -= lecture.getCredit();
            }
        } else {
            throw new IllegalArgumentException("수강 가능 학점이 부족합니다.");
        }
    }

    public void cancelEnrollment(Lecture lecture) {
        boolean result = this.enrollment.deleteLecture(lecture);
        if (result) {
            this.credit += lecture.getCredit();
        }
    }

}
