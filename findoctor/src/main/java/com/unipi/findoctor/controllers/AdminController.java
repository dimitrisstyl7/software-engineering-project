package com.unipi.findoctor.controllers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@NoArgsConstructor
@Controller
public class AdminController {
    @GetMapping({ADMIN_ROOT_URL, ADMIN_INDEX_URL})
    public String adminIndexPage() {
        return ADMIN_INDEX_FILE;
    }

    @GetMapping(ADMIN_ADD_LISTING_URL)
    public String adminAddListingPage() {
        return ADMIN_ADD_LISTING_FILE;
    }

    @GetMapping(ADMIN_BOOKINGS_URL)
    public String adminBookingsPage() {
        return ADMIN_BOOKINGS_FILE;
    }

    @GetMapping(ADMIN_CHARTS_URL)
    public String adminChartsPage() {
        return ADMIN_CHARTS_FILE;
    }

    @GetMapping(ADMIN_DOCTOR_PROFILE_URL)
    public String adminDoctorProfilePage() {
        return ADMIN_DOCTOR_PROFILE_FILE;
    }

    @GetMapping(ADMIN_MESSAGES_URL)
    public String adminMessagesPage() {
        return ADMIN_MESSAGES_FILE;
    }

    @GetMapping(ADMIN_PATIENT_PROFILE_URL)
    public String adminPatientProfilePage() {
        return ADMIN_PATIENT_PROFILE_FILE;
    }

    @GetMapping(ADMIN_REVIEWS_URL)
    public String adminReviewsPage() {
        return ADMIN_REVIEWS_FILE;
    }

    @GetMapping(ADMIN_TABLES_URL)
    public String adminTablesPage() {
        return ADMIN_TABLES_FILE;
    }
}
