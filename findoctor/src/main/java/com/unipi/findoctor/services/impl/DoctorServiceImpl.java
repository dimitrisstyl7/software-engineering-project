package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.DoctorDetailsDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    @Override
    public DoctorDetailsDto getDoctorDetailsByUsername(String username) {
        Doctor doctor = doctorRepository.findByUser_username(username);

        if (doctor.isVerified() == false) {
            return null;
        }

        return doctorMapper.mapToDoctorDetailsDto(doctor);
    }

    @Override
    public String getDoctorFullNameByUsername(String username) {
//        Doctor doctor = doctorRepository.findByUser_username(username);
//
//        if (doctor.isVerified() == false) {
//            return null;
//        }

        return "null";
    }
}
