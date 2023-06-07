package com.unipi.findoctor.services;

import com.unipi.findoctor.models.Doctor;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    Doctor findByAfm(String afm);
    void saveDoctor(Doctor doctor);
}
