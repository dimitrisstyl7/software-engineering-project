package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.RatingDto;
import com.unipi.findoctor.mappers.RatingMapper;
import com.unipi.findoctor.models.Rating;
import com.unipi.findoctor.repositories.RatingRepository;
import com.unipi.findoctor.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;

    @Override
    public RatingDto saveRating(RatingDto ratingDto) {
        // Map
        Rating rating = RatingMapper.mapToRating(ratingDto);

        //ratingRepository.saveRating(rating);
        return null;
    }
}
