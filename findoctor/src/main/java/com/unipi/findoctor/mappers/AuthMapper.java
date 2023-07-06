package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.RegistrationDto;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.models.User;

import static com.unipi.findoctor.constants.ControllerConstants.USER_TYPE_DOCTOR;
import static com.unipi.findoctor.constants.ControllerConstants.USER_TYPE_PATIENT;

public class AuthMapper {
    public static Doctor mapToDoctor(RegistrationDto registrationDto) {
        User user = User.builder()
                .username(registrationDto.getUsername())
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .userType(USER_TYPE_DOCTOR)
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .phone(registrationDto.getPersonalPhone())
                .build();

        return Doctor.builder()
                .afm(registrationDto.getAfm())
                .user(user)
                .dateOfBirth(registrationDto.getDateOfBirth())
                .specialization(registrationDto.getSpecialization())
                .businessPhone(registrationDto.getBusinessPhone())
                .city(registrationDto.getCity())
                .address(registrationDto.getAddress())
                .status("pending")
                .build();
    }

    public static Patient mapToPatient(RegistrationDto registrationDto) {
        User user = User.builder()
                .username(registrationDto.getUsername())
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .userType(USER_TYPE_PATIENT)
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .phone(registrationDto.getPersonalPhone())
                .build();

        return Patient.builder()
                .amka(registrationDto.getAmka())
                .user(user)
                .dateOfBirth(registrationDto.getDateOfBirth())
                .build();
    }
}
