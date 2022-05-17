package com.example.restservice.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.example.restservice.reservation.model.Reservation;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Reservation> reservation;

    public User(Long id){
        this.id = id;
    }

    @Override
    public String toString() {

        return "User [id=" + id + "]";
    }
}
