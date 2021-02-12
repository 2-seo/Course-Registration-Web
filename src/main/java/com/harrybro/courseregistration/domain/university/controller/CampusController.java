package com.harrybro.courseregistration.domain.university.controller;

import com.harrybro.courseregistration.domain.university.domain.Lecture;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CampusController {

    private final LectureRepository lectureRepository;

    @GetMapping("/campus")
    public String getCampus(Model model) {

        List<Lecture> lectures = lectureRepository.findAll();
        model.addAttribute("lectures", lectures);
        return "campus";
    }

}
