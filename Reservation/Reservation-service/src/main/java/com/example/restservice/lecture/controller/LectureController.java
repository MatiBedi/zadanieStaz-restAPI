package com.example.restservice.lecture.controller;

import com.example.restservice.lecture.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LectureController {

    private LectureService lectureService;

    @Autowired
    public LectureController( LectureService lectureService){ this.lectureService = lectureService; }


}
