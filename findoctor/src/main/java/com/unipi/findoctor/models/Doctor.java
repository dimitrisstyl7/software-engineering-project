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
@Table(name = "doctor_tbl")
public class Doctor {
    @Id
    private String afm;

    @OneToOne
    @JoinColumn(name = "registered_user_id", referencedColumnName = "user_id")
    private RegisteredUser registeredUser;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private List<Rating> ratings;

    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private boolean isVerified; // by default false
}
