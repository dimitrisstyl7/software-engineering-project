package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.models.Patient;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {
    Patient findByAmka(String amka);

    void savePatient(Patient patient);

    PatientDto findPatient(String username);

    Patient findPatientByUsername(String username);
}
