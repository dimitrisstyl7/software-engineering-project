package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
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
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private String review;
    private int ratingValue;
    private LocalDateTime date;
}
