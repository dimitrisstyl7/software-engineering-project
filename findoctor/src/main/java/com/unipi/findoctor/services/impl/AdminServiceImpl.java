package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.repositories.AdminRepository;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.repositories.PatientRepository;
import com.unipi.findoctor.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private DoctorMapper drmapper;


    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map((doctor) -> drmapper.mapToDoctorDto(doctor)).collect(Collectors.toList());
    }

    @Override
    public List<PatientDto> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map((patient) -> mapToPatientDto(patient)).collect(Collectors.toList());
    }

   @Override
    public DoctorDto findDoctorByAfm(String Afm) {
        Doctor doctor = doctorRepository.findByAfm(Afm);
        if (doctor == null) return null;
        return drmapper.mapToDoctorDto(doctor);
    }



    public PatientDto mapToPatientDto(Patient patient) {
        return PatientDto.builder()
                .amka(patient.getAmka())
                .user(patient.getUser())
                .dateOfBirth(patient.getDateOfBirth())
                .registeredOn(patient.getRegisteredOn())
                .build();
    }

}
