package com.unipi.findoctor.mappers;


import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.dto.RatingDto;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.models.Rating;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RatingMapper {
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;

    public Rating mapToRating(RatingDto ratingDto) {
        Patient patient = patientMapper.mapToPatient(ratingDto.getPatientDto());
        Doctor doctor = doctorMapper.mapToDoctor(ratingDto.getDoctorDto());

        return Rating.builder()
                .id(ratingDto.getId())
                .patient(patient)
                .doctor(doctor)
                .review(ratingDto.getReview())
                .ratingValue(ratingDto.getRatingValue())
                .date(ratingDto.getDate())
                .build();
    }

    public RatingDto mapToRatingDto(Rating rating) {
        PatientDto patientDto = patientMapper.mapToPatientDto(rating.getPatient());
        DoctorDto doctorDto = doctorMapper.mapToDoctorDto(rating.getDoctor());

        return RatingDto.builder()
                .id(rating.getId())
                .patientDto(patientDto)
                .doctorDto(doctorDto)
                .review(rating.getReview())
                .ratingValue(rating.getRatingValue())
                .date(rating.getDate())
                .build();
    }

    public List<RatingDto> mapToRatingDtoList(List<Rating> ratings) {
        return ratings.stream()
                .map(this::mapToRatingDto)
                .toList();
    }

    public List<Rating> mapToRatingList(List<RatingDto> ratingDtoList) {
        return ratingDtoList.stream()
                .map(this::mapToRating)
                .toList();
    }
}
