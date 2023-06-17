package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.models.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorDto mapToDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = DoctorDto.builder()
                .afm(doctor.getAfm())
                .user(doctor.getUser())
                .specialization(doctor.getSpecialization())
                .businessPhone(doctor.getBusinessPhone())
                .city(doctor.getCity())
                .imageURL(doctor.getImageURL())
                .status(doctor.getStatus())
                .address(doctor.getAddress())
                .ratings(doctor.getRatings())
                .build();

        return doctorDto;
    }
}
