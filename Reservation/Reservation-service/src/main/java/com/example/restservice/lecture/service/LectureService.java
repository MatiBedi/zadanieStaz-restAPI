package com.example.restservice.lecture.service;

import com.example.restservice.lecture.model.Lecture;
import com.example.restservice.lecture.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) { this.lectureRepository = lectureRepository; }

    public Optional<Lecture> find(Long id) {
        return lectureRepository.findById(id);
    }

    public List<Lecture> findAll() {return lectureRepository.findAll(); }

    @Transactional
    public Lecture create(Lecture lecture) {
        return lectureRepository.save(lecture);
    }
}
