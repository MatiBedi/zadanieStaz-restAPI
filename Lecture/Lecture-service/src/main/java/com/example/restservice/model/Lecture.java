package com.example.restservice.model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "topic")
    private String topic;
    @Column(name = "beginningTime")
    private LocalDateTime beginningTime;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = beginningTime.format(formatter);
        return "Lecture [id=" + id + ", title=" + title + ", topic=" + topic + ", date and time beginning time" + formattedDateTime + "]";
    }
}
