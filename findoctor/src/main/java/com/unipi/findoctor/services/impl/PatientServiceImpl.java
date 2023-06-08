package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.repositories.PatientRepository;
import com.unipi.findoctor.security.SecurityConfig;
import com.unipi.findoctor.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    }
}
