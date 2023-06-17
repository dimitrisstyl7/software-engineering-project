package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.mappers.PatientMapper;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.repositories.PatientRepository;
import com.unipi.findoctor.security.SecurityConfig;
import com.unipi.findoctor.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public Patient findByAmka(String amka) {
        return patientRepository.findByAmka(amka);
    }

    @Override
    public void savePatient(Patient patient) {
        patient.getUser().setPassword(SecurityConfig.passwordEncoder().encode(patient.getUser().getPassword()));
        patientRepository.save(patient);
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
