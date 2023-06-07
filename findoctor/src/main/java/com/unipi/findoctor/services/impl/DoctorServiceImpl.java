package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.security.SecurityConfig;
import com.unipi.findoctor.services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public Doctor findByAfm(String afm) {
        return doctorRepository.findByAfm(afm);
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        doctor.getUser().setPassword(SecurityConfig.passwordEncoder().encode(doctor.getUser().getPassword()));
        doctorRepository.save(doctor);
    }
}
