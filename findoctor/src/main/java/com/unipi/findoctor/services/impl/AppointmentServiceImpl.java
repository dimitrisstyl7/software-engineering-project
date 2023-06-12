package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.models.Appointment;
import com.unipi.findoctor.repositories.AppointmentRepository;
import com.unipi.findoctor.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    public Map<String, Boolean> getDoctorAvailableTimeSlots(String doctorUsername, LocalDate date) {

        Map<String, Boolean> time_slots = new LinkedHashMap<>();

        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctor_User_UsernameAndDate(doctorUsername, date);

        List<LocalTime> appointment_times = appointments.stream()
                .map(Appointment::getTime_slot)
                .toList();

        // Define the start and end time for available slots
        LocalTime startTime = LocalTime.of(8, 0); // 8:00
        LocalTime endTime = LocalTime.of(20, 0); // 20:00

        // Iterate through the time slots from start to end with one hour interval
        LocalTime currentSlot = startTime;
        while (!currentSlot.isAfter(endTime)) {
            // Check if the slot is not in the appointments list
            if (appointment_times.contains(currentSlot)) {
                time_slots.put(currentSlot.toString(), false);
            } else {
                time_slots.put(currentSlot.toString(), true);
            }

            // Move to the next slot
            currentSlot = currentSlot.plusHours(1);
        }


        return time_slots;
    }
}
