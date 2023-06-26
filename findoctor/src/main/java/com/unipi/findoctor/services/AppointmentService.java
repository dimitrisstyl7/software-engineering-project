package com.unipi.findoctor.services;

import com.unipi.findoctor.dto.AppointmentDetailsDto;
import com.unipi.findoctor.dto.AppointmentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface AppointmentService {
    Map<String, Boolean> getDoctorAvailableTimeSlots(String doctorUsername, LocalDate date);
    AppointmentDto saveAppointment(AppointmentDto appointment);
    List<AppointmentDetailsDto> fetchDoctorAppointments(String doctorUsername, LocalDate date);
    void deleteById(Long id, String doctorUsername);

}
