package com.unipi.findoctor.services;

import com.unipi.findoctor.models.Appointment;
import com.unipi.findoctor.models.Doctor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public interface AppointmentService {
    Map<String, Boolean> getDoctorAvailableTimeSlots(String username, LocalDate date);
}
