package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.models.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorDto mapToDoctorDto(Doctor doctor) {
        return DoctorDto.builder()
                .afm(doctor.getAfm())
                .user(doctor.getUser())
                .specialization(doctor.getSpecialization())
                .registeredOn(doctor.getRegisteredOn())
                .dateOfBirth(doctor.getDateOfBirth())
                .businessPhone(doctor.getBusinessPhone())
                .city(doctor.getCity())
                .imageURL(doctor.getImageURL())
                .status(doctor.getStatus())
                .address(doctor.getAddress())
                .ratings(doctor.getRatings())
                .views(doctor.getViews())
                .build();
    }
    public Doctor mapToDoctor(DoctorDto doctor) {
        return Doctor.builder()
                .afm(doctor.getAfm())
                .user(doctor.getUser())
                .dateOfBirth(doctor.getDateOfBirth())
                .registeredOn(doctor.getRegisteredOn())
                .specialization(doctor.getSpecialization())
                .businessPhone(doctor.getBusinessPhone())
                .city(doctor.getCity())
                .imageURL(doctor.getImageURL())
                .status(doctor.getStatus())
                .address(doctor.getAddress())
                .ratings(doctor.getRatings())
                .views(doctor.getViews())
                .build();
    }
}
