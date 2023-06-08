package com.unipi.findoctor.services;

import com.unipi.findoctor.models.Patient;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {
    Patient findByAmka(String amka);
    void savePatient(Patient patient);
}
