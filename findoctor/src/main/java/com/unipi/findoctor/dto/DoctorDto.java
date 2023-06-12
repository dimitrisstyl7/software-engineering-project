package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.Appointment;
import com.unipi.findoctor.models.Rating;
import com.unipi.findoctor.models.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DoctorDto {
    private String afm;
    private User user;
    private LocalDate dateOfBirth;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private boolean isVerified; // by default false
    private LocalDateTime registeredOn;
    private List<Appointment> appointments;
    private List<Rating> ratings;
}
