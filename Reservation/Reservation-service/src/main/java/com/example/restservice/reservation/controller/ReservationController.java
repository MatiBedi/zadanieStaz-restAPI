package com.example.restservice.reservation.controller;

import com.example.restservice.reservation.model.Reservation;
import com.example.restservice.lecture.service.LectureService;
import com.example.restservice.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api")
public class ReservationController {
    private ReservationService reservationService;
    private LectureService lectureService;

    @Autowired
    public ReservationController(ReservationService reservationService ,LectureService lectureService){
        this.reservationService = reservationService;
        this.lectureService = lectureService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> allReservations = reservationService.findAll();
        return new ResponseEntity<>(allReservations, HttpStatus.OK);
    }
    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") long id){
        Optional<Reservation> reservationFound = reservationService.find(id);
        if(reservationFound.isPresent()){
            return new ResponseEntity<>(reservationFound.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation reservation1 = reservationService.create(new Reservation(reservation.getLecture()));
        return new ResponseEntity<>(reservation1, HttpStatus.CREATED);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable("id") long id){
        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") long id,@RequestBody Reservation reservation){
        Optional<Reservation> reservationFound = reservationService.find(id);
        if(reservationFound.isPresent()){
            Reservation reservation1 = reservationFound.get();
            reservation1.setLecture(reservation.getLecture());
            return new ResponseEntity<>(reservationService.create(reservation1),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
