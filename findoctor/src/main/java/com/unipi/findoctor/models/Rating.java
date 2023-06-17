package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "rating_tbl")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_amka", referencedColumnName = "amka")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_afm", referencedColumnName = "afm")
    private Doctor doctor;

    private String review;
    private int ratingValue;

    @UpdateTimestamp
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Rating(" +
                "id=" + getId() +
                ", from=" + getPatient().getUser().getUsername() +
                ", to=" + getDoctor().getUser().getUsername() +
                ')';
    }
}
