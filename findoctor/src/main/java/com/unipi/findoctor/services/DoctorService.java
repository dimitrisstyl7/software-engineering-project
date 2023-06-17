package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.DoctorDetailsDto;
import com.unipi.findoctor.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    DoctorDetailsDto getDoctorDetailsByUsername(String username);
    Doctor findDoctor(String username);
    Boolean doctorExists(String username);
    Page<DoctorDetailsDto> getDoctorsByPage(String query, int pageNumber, int pageSize);

    int getDoctorViews(String username);

}
