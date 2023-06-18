package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.Rating;
import com.unipi.findoctor.models.User;
import com.unipi.findoctor.models.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private String afm;
    private User user;
    private LocalDate dateOfBirth;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private String imageURL;
    private String status;
    private List<Rating> ratings;
    private List<View> views;

    public double getAverageRating() {

        double average = ratings.stream()
                .mapToInt(rating -> rating.getRatingValue())
                .average()
                .orElse(0.0);

        return average;
    }

    public int ratingValuePercentage(int ratingValue) {

        double countWithRating = (int) ratings.stream()
                .filter(rating -> rating.getRatingValue() == ratingValue)
                .count();

        double totalCount = ratings.size();

        double percentage = countWithRating / totalCount * 100;

        return (int) percentage;
    }

    public String getFullName() {
        return "Dr. " + user.getName() + " " + user.getSurname();
    }

    @Override
    public String toString() {
        return "DoctorDetails(" +
                "for=" + getUser().getUsername() +
                ')';
    }
}
