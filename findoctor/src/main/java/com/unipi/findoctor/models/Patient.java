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
@Data
@Entity
@Table(name = "patient_tbl")
public class Patient {
    @Id
    private String amka;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "username")
    private User user;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private LocalDateTime registeredOn;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REFRESH)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REFRESH)
    private List<Rating> ratings;
}
