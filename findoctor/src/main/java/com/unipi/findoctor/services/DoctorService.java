package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    void saveDoctor(Doctor doctor);

    Doctor findByAfm(String afm);

    DoctorDto getDoctorDetailsByUsername(String username);

    Boolean doctorExists(String username);

    Page<DoctorDto> getDoctorsByPage(int pageNumber, int pageSize);
}
