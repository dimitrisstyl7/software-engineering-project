package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.DoctorDetailsDto;
import com.unipi.findoctor.models.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorDetailsDto mapToDoctorDetailsDto(Doctor doctor){
        DoctorDetailsDto doctorDetailsDto = DoctorDetailsDto.builder()
                .afm(doctor.getAfm())
                .user(doctor.getUser())
                .specialization(doctor.getSpecialization())
                .businessPhone(doctor.getBusinessPhone())
                .city(doctor.getCity())
                .address(doctor.getAddress())
                .appointments(doctor.getAppointments())
                .ratings(doctor.getRatings())
                .build();

        return doctorDetailsDto;
    }
}
