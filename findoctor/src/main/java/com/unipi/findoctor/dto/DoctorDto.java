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
    private String name;
    private String surname;
    private String status;
    private LocalDate dateOfBirth;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private String imageURL;

    public String getName_Surname(){
        return this.name + " " +this.surname;
    }
}
