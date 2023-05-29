package com.unipi.findoctor.services;

import com.unipi.findoctor.models.Patient;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {
    void addPatient();
    Patient getPatient(String amka);
}
