package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "registered_user_tbl")
public class RegisteredUser {
    @Id
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String personalPhone;

    @UpdateTimestamp
    private LocalDateTime registeredOn;

    @OneToOne(mappedBy = "registeredUser")
    private Doctor doctor;

    @OneToOne(mappedBy = "registeredUser")
    private Patient patient;
}
