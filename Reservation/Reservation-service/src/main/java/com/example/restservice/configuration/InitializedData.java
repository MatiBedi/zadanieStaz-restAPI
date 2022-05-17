package com.example.restservice.configuration;

import com.example.restservice.reservation.model.Reservation;
import com.example.restservice.reservation.service.ReservationService;
import com.example.restservice.lecture.service.LectureService;
import com.example.restservice.lecture.model.Lecture;
import com.example.restservice.user.model.User;
import com.example.restservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final ReservationService reservationService;
    private final LectureService lectureService;
    private final UserService userService;

    @Autowired
    public InitializedData(ReservationService reservationService, LectureService lectureService, UserService userService) {
        this.reservationService = reservationService;
        this.lectureService = lectureService;
        this.userService = userService;
    }

    @PostConstruct
    private synchronized void init() {
        Lecture lecture1 = Lecture.builder()
                .id(1L)
                .build();

        Lecture lecture2 = Lecture.builder()
                .id(2L)
                .build();

        Lecture lecture3 = Lecture.builder()
                .id(3L)
                .build();

        Lecture lecture4 = Lecture.builder()
                .id(4L)
                .build();

        Lecture lecture5 = Lecture.builder()
                .id(5L)
                .build();

        Lecture lecture6 = Lecture.builder()
                .id(6L)
                .build();

        Lecture lecture7 = Lecture.builder()
                .id(7L)
                .build();

        Lecture lecture8 = Lecture.builder()
                .id(8L)
                .build();

        Lecture lecture9 = Lecture.builder()
                .id(9L)
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
