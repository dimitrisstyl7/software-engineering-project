package com.unipi.findoctor.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String dateOfBirth;
    @NotEmpty
    private String personalPhone;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String userType;
    private LocalDateTime registeredOn;
    private String afm;
    private String amka;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private boolean isVerified; // by default false
}
