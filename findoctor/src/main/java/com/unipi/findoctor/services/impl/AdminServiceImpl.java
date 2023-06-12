package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.repositories.AdminRepository;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private DoctorRepository doctorRepository;

    @Autowired
    public AdminServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map((doctor) -> maptoDoctorDto(doctor)).collect(Collectors.toList());
    }

    private DoctorDto maptoDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = DoctorDto.builder()
                .ratings(doctor.getRatings())
                .dateOfBirth(doctor.getDateOfBirth())
                .isVerified(doctor.isVerified())
                .registeredOn(doctor.getRegisteredOn())
                .specialization(doctor.getSpecialization())
                .city(doctor.getCity())
                .appointments(doctor.getAppointments())
                .businessPhone(doctor.getBusinessPhone())
                .afm(doctor.getAfm())
                .address(doctor.getAddress())
                .user(doctor.getUser())
                .build();
        return doctorDto;
    }

}
