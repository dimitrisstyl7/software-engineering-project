package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.DoctorDetailsDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    DoctorDetailsDto getDoctorDetailsByUsername(String username);

    Boolean doctorExists(String username);

    Page<DoctorDetailsDto> getDoctorsByPage(int pageNumber, int pageSize);
}
