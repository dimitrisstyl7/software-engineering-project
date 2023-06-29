package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.RatingDto;
import com.unipi.findoctor.mappers.RatingMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.models.Rating;
import com.unipi.findoctor.repositories.RatingRepository;
import com.unipi.findoctor.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    @Override
    public RatingDto saveRating(RatingDto ratingDto) {
        // Map
        Rating rating = ratingMapper.mapToRating(ratingDto);
        rating = ratingRepository.save(rating);

        return ratingMapper.mapToRatingDto(rating);
    }

    public RatingDto findRating(Patient patient, Doctor doctor) {
        Rating rating = ratingRepository.findFirstByPatientAndDoctor(patient, doctor);
        return ratingMapper.mapToRatingDto(rating);
    }

    public RatingDto updateRating(RatingDto ratingDto) {
        Rating rating = ratingRepository.save(ratingMapper.mapToRating(ratingDto));
        return ratingMapper.mapToRatingDto(rating);
    }

    @Override
    public void deleteRating(RatingDto ratingDto) {
        ratingRepository.deleteById(ratingDto.getId());
    }

    @Override
    public boolean patientHasReviewed(Patient patient, Doctor doctor) {
        Rating rating = ratingRepository.findFirstByPatientAndDoctor(patient, doctor);
        if (rating == null) {
            return false;
        }
        return true;
    }
}
