package com.unipi.findoctor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    @NotEmpty(message = "Username is required.")
    private String username;

    @NotEmpty(message = "Name is required.")
    private String name;

    @NotEmpty(message = "Surname is required.")
    private String surname;

    @NotNull(message = "Date of birth is required.")
    @Past(message = "Date of birth must be in the past.")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Personal phone is required.")
    private String personalPhone;

    @NotEmpty(message = "Password is required.")
    private String password;

    @NotEmpty(message = "Email is required.")
    private String email;

    @NotNull(message = "Something went wrong. Please try again.")
    private Boolean isDoctor;

    private String afm;
    private String amka;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
}
