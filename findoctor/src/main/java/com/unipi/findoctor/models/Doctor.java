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
    private String status; // "pending" or "approved" or "rejected"
    private String imageName;

    @CreationTimestamp
    private LocalDateTime registeredOn;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REFRESH)
    @OrderBy("date DESC")
    private List<Rating> ratings;

    @Override
    public String toString() {
        return "Doctor(" +
                "afm=" + getAfm() +
                ", username='" + getUser().getUsername() + '\'' +
                ')';
    }

    public String getFullName() {
        return "Dr. " + user.getName() + " " + user.getSurname();
    }

}
