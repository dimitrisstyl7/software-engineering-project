package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.AppointmentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public interface AppointmentService {
    Map<String, Boolean> getDoctorAvailableTimeSlots(String username, LocalDate date);

    AppointmentDto saveAppointment(AppointmentDto appointment);
}
