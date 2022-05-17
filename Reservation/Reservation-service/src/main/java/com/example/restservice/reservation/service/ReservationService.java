package com.example.restservice.reservation.service;
import com.example.restservice.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restservice.lecture.model.Lecture;
import com.example.restservice.reservation.model.Reservation;
import com.example.restservice.lecture.repository.LectureRepository;
import com.example.restservice.reservation.repository.ReservationRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final LectureRepository lectureRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, LectureRepository lectureRepository) {
        this.reservationRepository = reservationRepository;
        this.lectureRepository = lectureRepository;
    }

    public Optional<Reservation> find(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findAll(Lecture lecture) { return reservationRepository.findAllByLecture(lecture); }

    public Optional<Reservation> find(Long lectureid, Long reservationid) {
        Optional<Lecture> lecture = lectureRepository.findById(lectureid);
        if (lecture.isPresent()) {
            return reservationRepository.findByLectureAndId(reservationid, lecture.get());
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    @Transactional
    public void delete(Long id) { reservationRepository.delete(reservationRepository.findById(id).get()); }



    public List<Reservation> findAll(User user) {return reservationRepository.findAllByUser(user);}
}
