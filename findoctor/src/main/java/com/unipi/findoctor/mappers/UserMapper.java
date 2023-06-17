package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.RegistrationDto;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.models.User;

public class UserMapper {
//    public static User mapToUser(RegistrationDto registrationDto) {
//        Doctor doctor = null;
//        Patient patient = null;
//
//        if (registrationDto.getIsDoctor()) {
//            doctor = Doctor.builder()
//                    .afm(registrationDto.getAfm())
//                    .dateOfBirth(registrationDto.getDateOfBirth())
//                    .specialization(registrationDto.getSpecialization())
//                    .businessPhone(registrationDto.getBusinessPhone())
//                    .city(registrationDto.getCity())
//                    .address(registrationDto.getAddress())
//                    .isVerified(false)
//                    .user(User.builder().username(registrationDto.getUsername()).build())
//                    .build();
//        } else { // patient
//            patient = Patient.builder()
//                    .amka(registrationDto.getAmka())
//                    .dateOfBirth(registrationDto.getDateOfBirth())
//                    .user(User.builder().username(registrationDto.getUsername()).build())
//                    .build();
//        }
//
//        return User.builder()
//                .username(registrationDto.getUsername())
//                .email(registrationDto.getEmail())
//                .password(registrationDto.getPassword())
//                .userType(registrationDto.getIsDoctor() ? "doctor" : "patient")
//                .name(registrationDto.getName())
//                .surname(registrationDto.getSurname())
//                .phone(registrationDto.getPersonalPhone())
//                .patient(patient)
//                .doctor(doctor)
//                .build();
//    }
}
