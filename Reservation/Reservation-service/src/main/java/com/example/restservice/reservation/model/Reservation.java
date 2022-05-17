package com.example.restservice.reservation.model;
import lombok.*;

import javax.persistence.*;
import com.example.restservice.user.model.User;
import com.example.restservice.lecture.model.Lecture;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lecture")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public Reservation(Lecture lecture, User user) {
        this.lecture = lecture;
        this.user = user;
    }

    public Reservation(Lecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public String toString() {

        return "Reservation [id=" + id + "]";
    }
}
