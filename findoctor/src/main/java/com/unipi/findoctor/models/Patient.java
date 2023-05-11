package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient_tbl")
public class Patient {
    @Id
    private String amka;

    @OneToOne
    @JoinColumn(name = "registered_user_id", referencedColumnName = "user_id")
    private RegisteredUser registeredUser;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    private List<Rating> ratings;
}
