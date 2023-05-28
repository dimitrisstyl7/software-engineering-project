package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.repositories.PatientRepository;
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
}
