package com.example.restservice.reservation.controller;

import com.example.restservice.reservation.model.Reservation;
import com.example.restservice.lecture.service.LectureService;
import com.example.restservice.reservation.service.ReservationService;
import com.example.restservice.lecture.model.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lectures/{lectureid}")
public class LectureReservationController {

    private ReservationService reservationService;
    private LectureService lectureService;

    @Autowired
    public LectureReservationController(ReservationService reservationService ,LectureService lectureService){
        this.reservationService = reservationService;
        this.lectureService = lectureService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable("lectureid") Long id) {
        Optional<Lecture> lecture = lectureService.find(id);
        if(lecture.isPresent()){
            List<Reservation> allReservations = reservationService.findAll(lecture.get());
            return new ResponseEntity<>(allReservations, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@PathVariable("lectureid") Long lectureid,
                                           @RequestBody Reservation reservation) {
        Optional<Lecture> lecture = lectureService.find(lectureid);
        if(lecture.isPresent()) {
            Reservation reservation1 = reservationService.create(new Reservation(lecture.get()));
            return new ResponseEntity<>(reservation1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
