package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.PatientDto;
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
    private AdminRepository adminRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;


    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map((doctor) -> maptoDoctorDto(doctor)).collect(Collectors.toList());
    }

    @Override
    public List<PatientDto> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map((patient) -> mapToPatientDto(patient)).collect(Collectors.toList());
    }

   @Override
    public DoctorDto findDoctorByAfm(String Afm) {
        Doctor doctor = doctorRepository.findByAfm(Afm);
        return maptoDoctorDto(doctor);
    }

    private DoctorDto maptoDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = DoctorDto.builder()
                //.ratings(doctor.getRatings())
                .dateOfBirth(doctor.getDateOfBirth())
                .status(doctor.getStatus())
                .specialization(doctor.getSpecialization())
                .city(doctor.getCity())
                //.appointments(doctor.getAppointments())
                .registeredOn(doctor.getRegisteredOn())
                .businessPhone(doctor.getBusinessPhone())
                .afm(doctor.getAfm())
                .address(doctor.getAddress())
                .user(doctor.getUser())
                .build();
        return doctorDto;
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
