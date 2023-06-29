package com.unipi.findoctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppointmentDetailsDto {
    private long id;
    private String amka;
    private String name;
    private String surname;
    private String phone;
    private LocalTime timeSlot;
}
