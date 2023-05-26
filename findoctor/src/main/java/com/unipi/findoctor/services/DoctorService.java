package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.DoctorDetailsDto;

public interface DoctorService {
    DoctorDetailsDto getDoctorDetailsByUsername(String username);
    String getDoctorFullNameByUsername(String username);
}
