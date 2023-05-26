package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.models.User;
import com.unipi.findoctor.repositories.PatientRepository;
import com.unipi.findoctor.services.PatientService;
import com.unipi.findoctor.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final UserService userService;

    @Override
    public void addPatient() {
        var x = Patient.builder()
                .user(User
                        .builder()
                        .email("email@dsd.com")
                        .name("name")
                        .password("password")
                        .phone("1234567890")
                        .surname("surname")
                        .userType("patient")
                        .username("username2")
                        .build())
                .amka("2")
                .appointments(null)
                .dateOfBirth(null)
                .ratings(null)
                .registeredOn(null)
                .build();
        patientRepository.save(x);
    }

    @Override
    public Patient getPatient(String amka) {
//        User user = userService.findByUsername("username2");
//        System.out.println(user);
//        Patient patient1 = patientRepository.findByUser_Username(user.getUsername());
//        Patient patient2 = patientRepository.findByAmka(amka);
//        System.out.println(patient1);
//        System.out.println(patient2);
//        System.out.println();
        return null;
    }
}
