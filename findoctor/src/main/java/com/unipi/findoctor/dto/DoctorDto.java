package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.Rating;
import com.unipi.findoctor.models.User;
import com.unipi.findoctor.models.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class DoctorDto {
    private String afm;
    private User user;
    private LocalDate dateOfBirth;
    private String specialization;
    private String businessPhone;
    private LocalDateTime registeredOn;
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

    public String getDoctorFullName() {
        return "Dr. " + getFullName();
    }

    public String getFullName() {
        return user.getName() + " " + user.getSurname();
    }

    public String getRegisteredDate() {
        // Split the date and time parts
        String[] parts = getRegisteredOn().toString().split("T");
        String datePart = parts[0];
        String timePart = parts[1].substring(0, 5); // Extract only the first 5 characters of the time

        // Join the date and time parts with a space in between
        String formattedDateTime = datePart + " " + timePart;

        return formattedDateTime;
    }

//    @Override
//    public String toString() {
//        return "DoctorDetails(" +
//                "for=" + getUser().getUsername() +
//                ')';
//    }
}
