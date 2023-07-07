package com.unipi.findoctor.services.impl;

import com.unipi.findoctor.dto.AppointmentDetailsDto;
import com.unipi.findoctor.dto.AppointmentDto;
import com.unipi.findoctor.mappers.AppointmentMapper;
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
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public Map<String, Boolean> getDoctorAvailableTimeSlots(String doctorUsername, LocalDate date) {

        Map<String, Boolean> timeSlots = new LinkedHashMap<>();

        // Define the start and end time for available slots
        LocalTime startTime = LocalTime.of(8, 0); // 8:00
        LocalTime endTime = LocalTime.of(20, 0); // 20:00

        // Check if the date is in the past
        LocalDate currentDate = LocalDate.now();
        if (date.isBefore(currentDate)) {
            // All time slots are unavailable

            LocalTime currentSlot = startTime;
            while (!currentSlot.isAfter(endTime)) {
                timeSlots.put(currentSlot.toString(), false);
                currentSlot = currentSlot.plusHours(1);
            }

            return timeSlots;
        }

        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctor_User_UsernameAndDate(doctorUsername, date);

        List<LocalTime> appointment_times = appointments.stream()
                .map(Appointment::getTimeSlot)
                .toList();

        // Get the current time
        LocalTime currentTime = LocalTime.now();

        // Iterate through the time slots from start to end with one hour interval
        LocalTime currentSlot = startTime;
        while (!currentSlot.isAfter(endTime)) {
            // Check if the slot is not in the appointments list
            if (currentSlot.isBefore(currentTime)) {
                timeSlots.put(currentSlot.toString(), false);
            }
            else if (appointment_times.contains(currentSlot)) {
                timeSlots.put(currentSlot.toString(), false);
            } else {
                timeSlots.put(currentSlot.toString(), true);
            }

            // Move to the next slot
            currentSlot = currentSlot.plusHours(1);
        }


        return timeSlots;
    }

    @Override
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        Appointment savedAppointment = appointmentRepository.save(appointmentMapper.mapToAppointment(appointmentDto));
        return appointmentMapper.mapToAppointmentDto(savedAppointment);
    }

    @Override
    public List<AppointmentDetailsDto> fetchDoctorAppointments(String doctorUsername, LocalDate date) {

        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctor_User_UsernameAndDate(doctorUsername, date);

        if (appointments.isEmpty()) {
            return null;
        }

        List<AppointmentDetailsDto> appointmentDetailDtoList = appointments.stream()
                .map(appointment -> appointmentMapper.mapToAppointmentDetailsDto(appointment))
                .toList();

        return appointmentDetailDtoList;
    }

    @Override
    public void deleteById(Long id, String doctorUsername) throws RuntimeException {
        if (!appointmentRepository.existsAppointmentByIdAndDoctor_User_Username(id, doctorUsername)) {
            throw new RuntimeException("Cannot delete");
        }

        appointmentRepository.deleteById(id);
    }

    @Override
    public void updateAppointment(Long id, String doctorUsername, LocalTime newTime) {
        if (!appointmentRepository.existsAppointmentByIdAndDoctor_User_Username(id, doctorUsername)) {
            throw new RuntimeException("Cannot update");
        }

        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty()) {
            throw new RuntimeException("Not found");
        }

        appointment.get().setTimeSlot(newTime);

        appointmentRepository.save(appointment.get());

    }
}
