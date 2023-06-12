package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.DoctorDetailsDto;
import com.unipi.findoctor.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    void saveDoctor(Doctor doctor);

    Doctor findByAfm(String afm);

    DoctorDetailsDto getDoctorDetailsByUsername(String username);

    Boolean doctorExists(String username);

    Page<DoctorDetailsDto> getDoctorsByPage(int pageNumber, int pageSize);
}
