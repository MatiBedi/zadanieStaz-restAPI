package com.example.restservice.lecture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import com.example.restservice.reservation.model.Reservation;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String topic;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime beginningTime;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Reservation> reservation;

    @Override
    public String toString() {

        return "Lecture [id=" + id + "]";
    }
}
