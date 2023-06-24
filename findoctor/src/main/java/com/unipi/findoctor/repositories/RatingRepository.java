package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    //Rating saveRating(Rating rating);
}
