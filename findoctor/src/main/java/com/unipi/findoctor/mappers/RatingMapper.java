package com.unipi.findoctor.mappers;


import com.unipi.findoctor.dto.RatingDto;
import com.unipi.findoctor.models.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
    public Rating mapToRating(RatingDto ratingDto) {
        return Rating.builder()
                .id(ratingDto.getId())
                .patient(ratingDto.getPatient())
                .doctor(ratingDto.getDoctor())
                .review(ratingDto.getReview())
                .ratingValue(ratingDto.getRatingValue())
                .date(ratingDto.getDate())
                .build();
    }

    public RatingDto mapToRatingDto(Rating rating) {
        return RatingDto.builder()
                .id(rating.getId())
                .patient(rating.getPatient())
                .doctor(rating.getDoctor())
                .review(rating.getReview())
                .ratingValue(rating.getRatingValue())
                .date(rating.getDate())
                .build();
    }
}
