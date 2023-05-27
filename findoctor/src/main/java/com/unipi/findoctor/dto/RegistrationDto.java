package com.unipi.findoctor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotEmpty
    private String personalPhone;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    @NotNull
    private Boolean isDoctor;

    private String afm;
    private String amka;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
}
