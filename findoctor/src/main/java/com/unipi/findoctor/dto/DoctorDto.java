package com.unipi.findoctor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class DoctorDto {
    private String afm;
    private LocalDate dateOfBirth;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private boolean isVerified;
    private String imageURL;
}
