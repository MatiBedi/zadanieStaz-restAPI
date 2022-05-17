package com.example.restservice.configuration;

import com.example.restservice.model.Lecture;
import com.example.restservice.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class InitializedData {

    private final LectureService lectureService;

    @Autowired
    public InitializedData(LectureService lectureService){
        this.lectureService = lectureService;
    }

    @PostConstruct
    private synchronized void init() {

        Lecture lecture1 = Lecture.builder()
                .title("Wykład pierwszy")
                .topic("Ścieżka tematyczna 1")
                .beginningTime(LocalDateTime.parse("2022-06-01T10:00"))
                .build();

        Lecture lecture2 = Lecture.builder()
                .title("Wykład drugi")
                .topic("Ścieżka tematyczna 2")
                .beginningTime(LocalDateTime.parse("2022-06-01T10:00"))
                .build();

        Lecture lecture3 = Lecture.builder()
                .title("Wykład trzeci")
                .topic("Ścieżka tematyczna 3")
                .beginningTime(LocalDateTime.parse("2022-06-01T10:00"))
                .build();

        Lecture lecture4 = Lecture.builder()
                .title("Wykład czwarty")
                .topic("Ścieżka tematyczna 1")
                .beginningTime(LocalDateTime.parse("2022-06-01T12:00"))
                .build();

        Lecture lecture5 = Lecture.builder()
                .title("Wykład piąty")
                .topic("Ścieżka tematyczna 2")
                .beginningTime(LocalDateTime.parse("2022-06-01T12:00"))
                .build();

        Lecture lecture6 = Lecture.builder()
                .title("Wykład szósty")
                .topic("Ścieżka tematyczna 3")
                .beginningTime(LocalDateTime.parse("2022-06-01T12:00"))
                .build();

        Lecture lecture7 = Lecture.builder()
                .title("Wykład siódmy")
                .topic("Ścieżka tematyczna 1")
                .beginningTime(LocalDateTime.parse("2022-06-01T14:00"))
                .build();

        Lecture lecture8 = Lecture.builder()
                .title("Wykład ósmy")
                .topic("Ścieżka tematyczna 2")
                .beginningTime(LocalDateTime.parse("2022-06-01T14:00"))
                .build();

        Lecture lecture9 = Lecture.builder()
                .title("Wykład dziewiąty")
                .topic("Ścieżka tematyczna 3")
                .beginningTime(LocalDateTime.parse("2022-06-01T14:00"))
                .build();
        lectureService.create(lecture1);
        lectureService.create(lecture2);
        lectureService.create(lecture3);
        lectureService.create(lecture4);
        lectureService.create(lecture5);
        lectureService.create(lecture6);
        lectureService.create(lecture7);
        lectureService.create(lecture8);
        lectureService.create(lecture9);

    }
}
