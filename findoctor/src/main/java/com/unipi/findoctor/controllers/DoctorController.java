package com.unipi.findoctor.controllers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@NoArgsConstructor
@Controller
public class DoctorController {
    @GetMapping({DOCTOR_ROOT_URL, DOCTOR_INDEX_URL})
    public String doctorIndexPage() {
        return DOCTOR_INDEX_FILE;
    }

    @GetMapping(DOCTOR_ADD_LISTING_URL)
    public String doctorAddListingPage() {
        return DOCTOR_ADD_LISTING_FILE;
    }

    @GetMapping(DOCTOR_BOOKINGS_URL)
    public String doctorBookingsPage() {
        return DOCTOR_BOOKINGS_FILE;
    }

    @GetMapping(DOCTOR_CHARTS_URL)
    public String doctorChartsPage() {
        return DOCTOR_CHARTS_FILE;
    }

    @GetMapping(DOCTOR_DOCTOR_PROFILE_URL)
    public String doctorDoctorProfilePage() {
        return DOCTOR_PROFILE_FILE;
    }

    @GetMapping(DOCTOR_MESSAGES_URL)
    public String doctorMessagesPage() {
        return DOCTOR_MESSAGES_FILE;
    }

    @GetMapping(DOCTOR_PATIENT_PROFILE_URL)
    public String doctorPatientProfilePage() {
        return DOCTOR_PATIENT_PROFILE_FILE;
    }

    @GetMapping(DOCTOR_REVIEWS_URL)
    public String doctorReviewsPage() {
        return DOCTOR_REVIEWS_FILE;
    }

    @GetMapping(DOCTOR_TABLES_URL)
    public String doctorTablesPage() {
        return DOCTOR_TABLES_FILE;
    }
}
