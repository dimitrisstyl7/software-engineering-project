package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.mappers.PatientMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.repositories.AdminRepository;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.repositories.PatientRepository;
import com.unipi.findoctor.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private DoctorMapper doctorMapper;
    private PatientMapper patientMapper;


    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map((doctor) -> doctorMapper.mapToDoctorDto(doctor)).collect(Collectors.toList());
    }

    @Override
    public List<PatientDto> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map((patient) -> patientMapper.mapToPatientDto(patient)).collect(Collectors.toList());
    }

    @Override
    public DoctorDto findDoctorByAfm(String Afm) {
        Doctor doctor = doctorRepository.findByAfm(Afm);
        if (doctor == null) return null;
        return doctorMapper.mapToDoctorDto(doctor);
    }
}
