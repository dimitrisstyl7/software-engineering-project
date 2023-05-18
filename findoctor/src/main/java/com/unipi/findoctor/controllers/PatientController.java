package com.unipi.findoctor.controllers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@NoArgsConstructor
@Controller
public class PatientController {
    @GetMapping({PATIENT_ROOT_URL, PATIENT_INDEX_URL})
    public String patientIndexPage() {
        return PATIENT_INDEX_FILE;
    }

    @GetMapping(PATIENT_ABOUT_URL)
    public String patientAboutPage() {
        return PATIENT_ABOUT_FILE;
    }

    @GetMapping(PATIENT_BOOKING_URL)
    public String patientBookingPage() {
        return PATIENT_BOOKING_FILE;
    }

    @GetMapping(PATIENT_CONTACT_US_URL)
    public String patientContactUsPage() {
        return PATIENT_CONTACT_US_FILE;
    }

    @GetMapping(PATIENT_DETAIL_PAGE_URL)
    public String patientDetailPage() {
        return PATIENT_DETAIL_PAGE_FILE;
    }

    @GetMapping(PATIENT_FAQ_URL)
    public String patientFaqPage() {
        return PATIENT_FAQ_FILE;
    }

    @GetMapping(PATIENT_GRID_LIST_URL)
    public String patientGridListPage() {
        return PATIENT_GRID_LIST_FILE;
    }

    @GetMapping(PATIENT_SUBMIT_REVIEW_URL)
    public String patientSubmitReviewPage() {
        return PATIENT_SUBMIT_REVIEW_FILE;
    }
}
