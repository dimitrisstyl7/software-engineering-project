package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.models.Patient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PatientService {
    Patient findByAmka(String amka);

    PatientDto findPatient(String username);
    Patient findPatientByUsername(String username);
}
