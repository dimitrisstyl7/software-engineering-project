package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.models.Patient;

public class PatientMapper {

    public static PatientDto mapToPatientDto(Patient patient) {
        PatientDto patientDto = PatientDto.builder()
                .amka(patient.getAmka())
                .user(patient.getUser())
                .dateOfBirth(patient.getDateOfBirth())
                .registeredOn(patient.getRegisteredOn())
                .build();

        return patientDto;
    }
}
