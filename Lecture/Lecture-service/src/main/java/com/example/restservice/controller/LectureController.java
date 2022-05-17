package com.example.restservice.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.restservice.model.Lecture;
import com.example.restservice.repository.LectureRepository;
import com.example.restservice.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LectureController {

    private LectureService lectureService;

    @Autowired
    public LectureController( LectureService lectureService){ this.lectureService = lectureService; }

    @GetMapping("/lectures")
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> allLectures = lectureService.findAll();
        return new ResponseEntity<>(allLectures, HttpStatus.OK);
    }
    @GetMapping("/lectures/{id}")
    public ResponseEntity<Optional<Lecture>> getLecture(@PathVariable("id") long id){

        Optional<Lecture> lectureFound = lectureService.find(id);
        if(lectureFound.isPresent()){
            return new ResponseEntity<>(lectureFound, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
