package com.unipi.findoctor.controllers;

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
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RestApiController {
    private DoctorService doctorService;
    private AppointmentService appointmentService;

    @GetMapping("/available-time-slots")
    public ResponseEntity<Map<String, Boolean>> scheduleAppointment(@RequestParam(value = "date", required = true) String date_string,
                                                                    @RequestParam(value = "doctorUsername", required = true) String doctorUsername) {

        LocalDate date;

        // Check if date is in the correct format
        try {
            date = LocalDate.parse(date_string);
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
}
