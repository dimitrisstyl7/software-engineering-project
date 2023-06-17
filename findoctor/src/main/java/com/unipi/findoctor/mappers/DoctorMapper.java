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
                .imageURL(doctor.getImageURL())
                .address(doctor.getAddress())
                .ratings(doctor.getRatings())
                .views(doctor.getViews())
                .build();

        return doctorDetailsDto;
    }
}
