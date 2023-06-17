package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.mappers.PatientMapper;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.repositories.PatientRepository;
import com.unipi.findoctor.services.PatientService;
import com.unipi.findoctor.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final UserService userService;

    @Override
    public Patient findByAmka(String amka) {
        return null;
    }

    @Override
    public PatientDto findPatient(String username) {
        Optional<Patient> optionalPatient = patientRepository.findByUser_username(username);

        if (optionalPatient.isEmpty()) {
            return  null;
        }

        Patient patient = optionalPatient.orElseThrow(() -> new RuntimeException("Patient not found"));

        return PatientMapper.mapToPatientDto(patient);
    }

    @Override
    public Patient findPatientByUsername(String username) {
        Optional<Patient> optionalPatient = patientRepository.findByUser_username(username);

        if (optionalPatient.isEmpty()) {
            return  null;
        }

        return optionalPatient.orElseThrow(() -> new RuntimeException("Patient not found"));

    }
}
