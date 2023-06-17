package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PatientDto {
    private String amka;
    private User user;
    private LocalDate dateOfBirth;
    private LocalDateTime registeredOn;

}
