package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.DoctorDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    public List<DoctorDto> findAllDoctors();
}
