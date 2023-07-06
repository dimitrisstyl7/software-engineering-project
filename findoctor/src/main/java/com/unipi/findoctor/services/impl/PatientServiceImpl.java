package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.mappers.PatientMapper;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.repositories.PatientRepository;
import com.unipi.findoctor.security.SecurityConfig;
import com.unipi.findoctor.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.unipi.findoctor.constants.ControllerConstants.USER_TYPE_PATIENT;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public Patient findByAmka(String amka) {
        return patientRepository.findByAmka(amka);
    }

    @Override
    public void savePatient(Patient patient) {
        patient.getUser().setPassword(SecurityConfig.passwordEncoder().encode(patient.getUser().getPassword()));
        patientRepository.save(patient);
    }

    @Override
    public PatientDto findPatient(String username) {
        Patient patient = patientRepository.findByUser_username(username);

        if (patient == null) {
            return null;
        }
        return patientMapper.mapToPatientDto(patient);
    }

    @Override
    public Patient findPatientByUsername(String username) {
        Patient patient = patientRepository.findByUser_username(username);

        if (patient == null) {
            return null;
        }

        return patient;
    }

    @Override
    public void updatePatient(Patient patient) {
        Patient existingPatient = patientRepository.findByUser_username(patient.getUser().getUsername());

        if (existingPatient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        existingPatient.getUser().setName(patient.getUser().getName());
        existingPatient.getUser().setSurname(patient.getUser().getSurname());
        existingPatient.getUser().setEmail(patient.getUser().getEmail());
        existingPatient.getUser().setPhone(patient.getUser().getPhone());

        patientRepository.save(existingPatient);
    }
}
