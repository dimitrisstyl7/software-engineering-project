package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    DoctorDto getDoctorDetailsByUsername(String username);

    void saveDoctor(Doctor doctor);

    Doctor findByAfm(String afm);

    Doctor findDoctor(String username);

    boolean doctorExists(String username);

    Page<DoctorDto> getDoctorsByPage(String query, int pageNumber, int pageSize);

    void updateDoctor(Doctor doctor);
}
