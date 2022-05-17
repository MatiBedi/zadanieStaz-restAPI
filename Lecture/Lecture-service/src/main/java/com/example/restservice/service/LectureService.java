package com.example.restservice.service;

import com.example.restservice.model.Lecture;
import com.example.restservice.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }

    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Transactional
    public Lecture create(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public Optional<Lecture> find(Long id) {
        return lectureRepository.findById(id);
    }
}
