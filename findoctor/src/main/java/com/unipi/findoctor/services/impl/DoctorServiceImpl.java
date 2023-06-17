package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.security.SecurityConfig;
import com.unipi.findoctor.services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDto getDoctorDetailsByUsername(String username) {
        Doctor doctor = doctorRepository.findByUser_username(username);

        if (doctor == null) {
            return null;
        }

        if (doctor.isVerified() == false) {
            return null;
        }

        return doctorMapper.mapToDoctorDetailsDto(doctor);
    }

    @Override
    public Boolean doctorExists(String username) {
        Optional<Doctor> doctor = Optional.ofNullable(doctorRepository.findByUser_username(username));

        if (doctor.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public Page<DoctorDto> getDoctorsByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);
        Page<DoctorDto> doctorDetailsDtoPage = doctorPage.map(doctor -> doctorMapper.mapToDoctorDetailsDto(doctor));

        return doctorDetailsDtoPage;
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        doctor.getUser().setPassword(SecurityConfig.passwordEncoder().encode(doctor.getUser().getPassword()));
        doctorRepository.save(doctor);
    }

    @Override
    public Doctor findByAfm(String afm) {
        return doctorRepository.findByAfm(afm);
    }
}
