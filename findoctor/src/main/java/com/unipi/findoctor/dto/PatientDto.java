package com.unipi.findoctor.dto;

import com.unipi.findoctor.models.Appointment;
import com.unipi.findoctor.models.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Builder
@Data
public class PatientDto {

    private String amka;
    private UserDto userDto;
    private LocalDate dateOfBirth;
    private LocalDateTime registeredOn;
    private List<Appointment> appointments;
    private List<Rating> ratings;

    public int getCountReviews() {
        return ratings.size();
    }

    public int getCountAppointments() {
        return appointments.size();
    }

    public String getNextAppointment() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("ha");

        Optional<Appointment> nextAppointment = appointments.stream()
                .filter(appointment -> appointment.getDate().isAfter(today) || appointment.getDate().isEqual(today))
                .min(Comparator.comparing(Appointment::getDate));

        Appointment closestAppointment;
        if (nextAppointment.isPresent()) {
            closestAppointment = nextAppointment.get();
            String formattedDate = closestAppointment.getDate().format(dateFormatter);
            String formattedTimeSlot = closestAppointment.getTimeSlot().format(timeFormatter);

            return formattedDate + ", " + formattedTimeSlot;
        } else {
            return null;
        }


    }

    public String getFullName() {
        return userDto.getName() + " " + userDto.getSurname();
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
