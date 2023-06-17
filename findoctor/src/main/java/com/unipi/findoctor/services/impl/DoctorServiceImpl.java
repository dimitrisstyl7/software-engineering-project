package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.repositories.ViewRepository;
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
    private final ViewRepository viewRepository;

    @Override
    public DoctorDto getDoctorDetailsByUsername(String username) {
        Doctor doctor = doctorRepository.findByUser_username(username);

        if (doctor == null) {
            return null;
        }

        if (!doctor.getStatus().equals("approved")) {
            return null;
        }

        return doctorMapper.mapToDoctorDto(doctor);
    }

    @Override
    public Doctor findDoctor(String username) {
        return doctorRepository.findByUser_username(username);
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
    public Page<DoctorDto> getDoctorsByPage(String query, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Doctor> doctorsPage;

        if (query == null) {
            doctorsPage = doctorRepository.findAll(pageable);
        } else {
            doctorsPage = doctorRepository.searchDoctors(query, pageable);
        }

        return doctorsPage.map(doctorMapper::mapToDoctorDto);
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

    public int getDoctorViews(String username) {
        return viewRepository.countByDoctor_User_username(username);
    }

}
