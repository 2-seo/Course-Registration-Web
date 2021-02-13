package com.harrybro.courseregistration.domain.university.repository;

import com.harrybro.courseregistration.domain.university.domain.Lecture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LectureRepositoryTest {

    @Autowired
    private LectureRepository lectureRepository;

    @Test
    void findByNameContaining() {
        List<Lecture> lectures = lectureRepository.findByNameContaining("경");
        Assertions.assertThat(lectures.get(0).getName())
                .isNotEmpty()
                .contains("경");
        for (Lecture lecture : lectures) {
            System.out.println(lecture.getName());
        }
    }

}