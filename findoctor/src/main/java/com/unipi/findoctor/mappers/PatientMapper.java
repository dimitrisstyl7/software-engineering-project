package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.models.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientDto mapToPatientDto(Patient patient) {
        return PatientDto.builder()
                .amka(patient.getAmka())
                .user(patient.getUser())
                .dateOfBirth(patient.getDateOfBirth())
                .registeredOn(patient.getRegisteredOn())
                .build();
    }

    public Patient mapToPatient(PatientDto patientDto) {
        return Patient.builder()
                .amka(patientDto.getAmka())
                .user(patientDto.getUser())
                .dateOfBirth(patientDto.getDateOfBirth())
                .registeredOn(patientDto.getRegisteredOn())
                .build();
    }
}
