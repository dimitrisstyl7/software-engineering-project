package com.unipi.findoctor.controllers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@NoArgsConstructor
@Controller
public class DoctorController {

    @GetMapping(DOCTOR_INDEX_URL)
    public String doctorIndex() {
        return DOCTOR_INDEX_FILE;
    }

    @GetMapping(DOCTOR_ADD_LISTING_URL)
    public String doctorAddListing() {
        return DOCTOR_ADD_LISTING_FILE;
    }

    @GetMapping(DOCTOR_BOOKINGS_URL)
    public String doctorBookings() {
        return DOCTOR_BOOKINGS_FILE;
    }

    @GetMapping(DOCTOR_CHARTS_URL)
    public String doctorCharts() {
        return DOCTOR_CHARTS_FILE;
    }

    @GetMapping(DOCTOR_DOCTOR_PROFILE_URL)
    public String doctorDoctorProfile() {
        return DOCTOR_PROFILE_FILE;
    }

    @GetMapping(DOCTOR_MESSAGES_URL)
    public String doctorMessages() {
        return DOCTOR_MESSAGES_FILE;
    }

    @GetMapping(DOCTOR_PATIENT_PROFILE_URL)
    public String doctorPatientProfile() {
        return DOCTOR_PATIENT_PROFILE_FILE;
    }

    @GetMapping(DOCTOR_REVIEWS_URL)
    public String doctorReviews() {
        return DOCTOR_REVIEWS_FILE;
    }

    @GetMapping(DOCTOR_TABLES_URL)
    public String doctorTables() {
        return DOCTOR_TABLES_FILE;
    }
}
