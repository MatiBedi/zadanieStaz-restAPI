package com.example.restservice.reservation.repository;

import com.example.restservice.lecture.model.Lecture;
import com.example.restservice.user.model.User;
import com.example.restservice.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    List<Reservation> findAllByLecture(Lecture lecture);
    Optional<Reservation> findByLectureAndId (Long id, Lecture lecture);

    List<Reservation> findAllByUser(User user);
}
