package com.example.restservice.reservation.controller;

import com.example.restservice.lecture.model.Lecture;
import com.example.restservice.lecture.service.LectureService;
import com.example.restservice.reservation.model.Reservation;
import com.example.restservice.reservation.service.ReservationService;
import com.example.restservice.user.model.User;
import com.example.restservice.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/users/{userid}")
public class UserReservationController {

    private ReservationService reservationService;
    private UserService userService;
    private LectureService lectureService;
    private RestTemplate restTemplate;
    private ObjectMapper mapper;
    @Autowired
    public UserReservationController(ReservationService reservationService ,UserService userService, LectureService lectureService){
        this.reservationService = reservationService;
        this.userService = userService;
        this.lectureService = lectureService;
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable("userid") Long id) {
        Optional<User> user = userService.find(id);
        if(user.isPresent()){
            List<Reservation> allReservations = reservationService.findAll(user.get());
            return new ResponseEntity<>(allReservations, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lectures")        // wyświetlanie wszystkich lecture danego usera
    public ResponseEntity<List<Lecture>> getLectures(@PathVariable("userid") Long id){
        Optional<User> user = userService.find(id);
        if(user.isPresent()){
            List<Reservation> allReservations = reservationService.findAll(user.get());
            List<Lecture> lectures = new ArrayList<>();
            String uriConst = "http://localhost:8081/api/lectures/";
            for(Reservation x : allReservations){
                String uri = uriConst + x.getLecture().getId();
                Lecture response = restTemplate.getForObject(uri, Lecture.class);
                lectures.add(response);
            }
            return new ResponseEntity<>(lectures, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reservations")
    public ResponseEntity createReservation(@PathVariable("userid") Long userid,
                                                         @RequestBody Lecture lecture){
        Long id = lecture.getId();
        Optional<User> user = userService.find(userid);
        Optional<Lecture> lectureFound = lectureService.find((id));
        String uriLecturesReservations = "http://localhost:8080/api/lectures/" + id +"/reservations";

        if(user.isPresent() && lectureFound.isPresent()) {

            // sprawdzanie ilości użytkowników w tym wykładzie
            List<Reservation> lecturesReservations = restTemplate.getForObject(uriLecturesReservations, List.class);

            if(lecturesReservations.size() < 5) {
                Reservation reservation1 = reservationService.create(new Reservation(lectureFound.get(), user.get()));

                try {
                    File f = new File("powiadomienie.txt");
                    if (f.createNewFile()) {
                        System.out.println("File created: " + f.getName());
                    } else {
                        System.out.println("File already exists.");
                    }
                    Date date = Calendar.getInstance().getTime();

                    FileWriter myWriter = new FileWriter("powiadomienie.txt");
                    myWriter.write("Data wysłania: " + date + "\n");
                    myWriter.write("Do: \n" );
                    myWriter.write("Treść: Twoja rezerwacja o id: " + reservation1.getId() + " została stworzona");
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                return new ResponseEntity<>(reservation1, HttpStatus.CREATED);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Zbyt wielu uczestników w wykładzie, nie stworzono rezerwacji");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wprowadzono złe dane, nie stworzono rezerwacji");
    }
}
