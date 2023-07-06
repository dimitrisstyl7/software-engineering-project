package com.unipi.findoctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDto {
    private long id;
    private PatientDto patientDto;
    private DoctorDto doctorDto;
    private String review;
    private int ratingValue;
    private LocalDateTime date;
}
