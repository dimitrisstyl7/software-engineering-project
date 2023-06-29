package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private String afm;
    private UserDto user;
    private LocalDate dateOfBirth;
    private String specialization;
    private String businessPhone;
    private String city;
    private String address;
    private String imageName;
    private String status;
    private LocalDateTime registeredOn;
    private List<Rating> ratings;

    public double getAverageRating() {

        double average = ratings.stream()
                .mapToInt(rating -> rating.getRatingValue())
                .average()
                .orElse(0.0);

        return Math.round(average * 10.0) / 10.0;
    }

    public long getRoundAverageRating() {
        double average = getAverageRating();
        return Math.round(average);
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

    public String getRegisteredDate() {
        // Split the date and time parts
        String[] parts = getRegisteredOn().toString().split("T");
        String datePart = parts[0];
        String timePart = parts[1].substring(0, 5); // Extract only the first 5 characters of the time

        // Join the date and time parts with a space in between
        return datePart + " " + timePart;
    }
}