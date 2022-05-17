package com.example.restservice.reservation.controller;

import com.example.restservice.lecture.model.Lecture;
import com.example.restservice.lecture.service.LectureService;
import com.example.restservice.reservation.model.Reservation;
import com.example.restservice.reservation.service.ReservationService;
import com.example.restservice.user.model.User;
import com.example.restservice.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private ReservationService reservationService;
    private UserService userService;
    private LectureService lectureService;
    private RestTemplate restTemplate;
    private ObjectMapper mapper;
    @Autowired
    public AdminController(ReservationService reservationService ,UserService userService, LectureService lectureService){
        this.reservationService = reservationService;
        this.userService = userService;
        this.lectureService = lectureService;
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
    }

    @GetMapping("")
    public ResponseEntity<List<String>> getAttendance(){
        List<Lecture> allLectures = lectureService.findAll();
        List<String> results = new ArrayList<>();
        double attendanceTopic1 =0;
        double attendanceTopic2 =0;
        double attendanceTopic3 =0;
        String uriConst = "http://localhost:8080/api/lectures/";
        for(Lecture x : allLectures){
            String s = "Attendance of the lecture with id: " + x.getId() + " was ";
            String uri = uriConst + x.getId() + "/reservations";
            List<Reservation> lecturesReservations = restTemplate.getForObject(uri, List.class);
            double tmp = (lecturesReservations.size() / 5.0) *100;
            s = s + lecturesReservations.size() + " out of 5 which is equal to " + tmp + "%";

            if(x.getId() % 3L == 1L ) {     // ścieżka tematyczna 1
                attendanceTopic1 += lecturesReservations.size();
            }
            else if(x.getId() % 3L == 2L ) {// ścieżka tematyczna 2
                attendanceTopic2 += lecturesReservations.size();
            }
            else {                          // ścieżka tematyczna 3
                attendanceTopic3 += lecturesReservations.size();
            }


            results.add(s);
        }
        double tmp = (attendanceTopic1 / 15.0) * 100;
        String resultRounded1 = String.format("%.2f", tmp);
        String s1 = "Lectures with id 1,4,7 were covering topic: ścieżka1. The attendance of those lectures was "
                + attendanceTopic1 + " out of 15 which is equal to " + resultRounded1 + "%";
        results.add(s1);

        tmp = (attendanceTopic2 / 15.0) * 100;
        String resultRounded2 = String.format("%.2f", tmp);
        String s2 = "Lectures with id 2,5,8 were covering topic: ścieżka2. The attendance of those lectures was "
                + attendanceTopic2 + " out of 15 which is equal to " + resultRounded2 + "%";
        results.add(s2);

        tmp = (attendanceTopic3 / 15.0) * 100;
        String resultRounded3 = String.format("%.2f", tmp);
        String s3 = "Lectures with id 3,6,9 were covering topic: ścieżka3. The attendance of those lectures was "
                + attendanceTopic3 + " out of 15 which is equal to " + resultRounded3 + "%";
        results.add(s3);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }


}
