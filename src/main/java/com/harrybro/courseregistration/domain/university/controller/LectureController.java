package com.harrybro.courseregistration.domain.university.controller;

import com.harrybro.courseregistration.domain.university.domain.lecture.Lecture;
import com.harrybro.courseregistration.domain.university.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class LectureController {

    private final LectureRepository lectureRepository;

    @GetMapping("/lecture")
    public String getLecture(Model model) {

        List<Lecture> lectures = lectureRepository.findAll();
        model.addAttribute("lectures", lectures);
        return "lecture";
    }

    @GetMapping("/lecture/search")
    public String getLectureContaining(@RequestParam(required = false) String name, @RequestParam(required = false) String lecturer, Model model) {
        List<Lecture> lectures = null;
        if (name == null && lecturer == null) {
            return "redirect:/lecture";
        } else if (lecturer == null) {
            lectures = lectureRepository.findByNameContaining(name);
        } else if (name == null) {
            lectures = lectureRepository.findByLecturerContaining(lecturer);
        } else {
            lectures = lectureRepository.findByNameContainingOrLecturerContaining(name, lecturer);
        }
        model.addAttribute("lectures", lectures);
        return "lecture";
    }

} 
