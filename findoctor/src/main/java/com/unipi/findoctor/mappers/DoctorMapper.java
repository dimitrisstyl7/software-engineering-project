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
                .businessPhone(doctor.getBusinessPhone())
                .city(doctor.getCity())
                .imageName(doctor.getImageName())
                .status(doctor.getStatus())
                .address(doctor.getAddress())
                .ratings(doctor.getRatings())
                .views(doctor.getViews())
                .build();
    }
    public Doctor mapToDoctor(DoctorDto doctorDto) {
        return Doctor.builder()
                .afm(doctorDto.getAfm())
                .user(doctorDto.getUser())
                .specialization(doctorDto.getSpecialization())
                .businessPhone(doctorDto.getBusinessPhone())
                .city(doctorDto.getCity())
                .imageName(doctorDto.getImageName())
                .status(doctorDto.getStatus())
                .address(doctorDto.getAddress())
                .ratings(doctorDto.getRatings())
                .views(doctorDto.getViews())
                .build();
    }



}
