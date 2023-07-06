package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.PatientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<DoctorDto> findAllDoctors();

    List<PatientDto> findAllPatients();

    DoctorDto findDoctorByAfm(String Afm);
}
