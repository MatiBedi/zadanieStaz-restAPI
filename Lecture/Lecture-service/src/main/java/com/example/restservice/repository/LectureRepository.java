package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restservice.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
