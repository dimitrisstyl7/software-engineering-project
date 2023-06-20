package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.RatingDto;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;

public interface RatingService {
    RatingDto saveRating(RatingDto ratingDto);

    boolean patientHasReviewed(Patient patient, Doctor doctor);

    RatingDto findRating(Patient patient, Doctor doctor);

    RatingDto updateRating(RatingDto ratingDto);

    void deleteRating(RatingDto ratingDto);
}
