package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "doctor_tbl")
@Data
public class Doctor {
    @Id
    private String afm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "username")
    private User user;

    private LocalDate dateOfBirth;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private boolean isVerified;
    private String imageURL;


    @CreationTimestamp
    private LocalDateTime registeredOn;

//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REFRESH)
//    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REFRESH)
    private List<Rating> ratings;
}
