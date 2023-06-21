package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PatientDto {
    private String amka;
    private User user;
    private LocalDate dateOfBirth;
    private LocalDateTime registeredOn;

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
}
