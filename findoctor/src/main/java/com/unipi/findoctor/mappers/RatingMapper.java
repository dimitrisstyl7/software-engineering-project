package com.unipi.findoctor.mappers;


import com.unipi.findoctor.dto.RatingDto;
import com.unipi.findoctor.models.Rating;
public class RatingMapper {
    public static Rating mapToRating(RatingDto ratingDto){
        Rating rating = Rating.builder()
                .review(ratingDto.getReview())
                .ratingValue(ratingDto.getRatingValue())
                .build();

        return rating;
    }
}
