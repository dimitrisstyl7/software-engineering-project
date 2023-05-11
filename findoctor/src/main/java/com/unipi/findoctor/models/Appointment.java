package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointment_tbl")
public class Appointment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "patient_amka", referencedColumnName = "amka")
    private Patient patient;

    @Id
    @ManyToOne
    @JoinColumn(name = "doctor_afm", referencedColumnName = "afm")
    private Doctor doctor;

    @Id
    private LocalDateTime dateTime;
}
