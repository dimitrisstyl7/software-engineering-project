package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppointmentDto {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time_slot;
}
