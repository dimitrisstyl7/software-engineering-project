package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.AppointmentDetailsDto;
import com.unipi.findoctor.services.AppointmentService;
import com.unipi.findoctor.services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RestApiController {
    private DoctorService doctorService;
    private AppointmentService appointmentService;

    @GetMapping("/available-time-slots")
    public ResponseEntity<Map<String, Boolean>> scheduleAppointment(@RequestParam(value = "date", required = true) String dateString,
                                                                    @RequestParam(value = "doctorUsername", required = true) String doctorUsername) {

        LocalDate date;

        // Check if date is in the correct format
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        // Check if the doctorUsername exists
        if (!doctorService.doctorExists(doctorUsername)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Map<String, Boolean> time_slots = appointmentService.getDoctorAvailableTimeSlots(doctorUsername, date);

        // Return a response
        return ResponseEntity.ok(time_slots);
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDetailsDto>> getAppointments(@RequestParam(value = "date") String dateString,
                                                 @RequestParam(value = "doctorUsername") String doctorUsername) {
        LocalDate date;

        // Check if date is in the correct format
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        // Check if the doctorUsername exists
        if (!doctorService.doctorExists(doctorUsername)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<AppointmentDetailsDto> appointmentDetailDtos = appointmentService.fetchDoctorAppointments(doctorUsername, date);

        return ResponseEntity.ok(appointmentDetailDtos);
    }
}
