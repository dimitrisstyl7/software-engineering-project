package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doctor_tbl")
public class Doctor {
    @Id
    private String afm;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(referencedColumnName = "username")
    private User user;

    private LocalDate dateOfBirth;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private boolean isVerified; // by default false

    @UpdateTimestamp
    private LocalDateTime registeredOn;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private List<Rating> ratings;
}
