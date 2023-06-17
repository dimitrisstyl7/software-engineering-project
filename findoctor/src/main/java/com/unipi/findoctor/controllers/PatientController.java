package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@AllArgsConstructor
@Controller
public class PatientController {
    private DoctorService doctorService;

    @GetMapping({PATIENT_ROOT_URL, PATIENT_INDEX_URL_1})
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
    public String patientDetailPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {

        DoctorDto doctorDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        model.addAttribute("doctorDetails", doctorDto);
        return PATIENT_DETAIL_PAGE_FILE;
    }

    @GetMapping(PATIENT_FAQ_URL)
    public String patientFaqPage() {
        return PATIENT_FAQ_FILE;
    }

    @GetMapping(PATIENT_GRID_LIST_URL)
    public String patientGridListPage(@RequestParam(defaultValue = "1") String page, Model model) {

        int adjustedPageNumber;

        try {
            adjustedPageNumber = Integer.parseInt(page) - 1;
        } catch (NumberFormatException e) {
            return "error/404";
        }

        if (adjustedPageNumber < 0) {
            return "error/404";
        }

        Page<DoctorDto> doctorDetailsDtoPage = doctorService.getDoctorsByPage(adjustedPageNumber, 9);

        model.addAttribute("doctorDetails", doctorDetailsDtoPage.getContent());
        model.addAttribute("currentPage", adjustedPageNumber + 1);
        model.addAttribute("totalPages", doctorDetailsDtoPage.getTotalPages());
        model.addAttribute("totalDoctors", doctorDetailsDtoPage.getTotalElements());
        return PATIENT_GRID_LIST_FILE;
    }

    @GetMapping(PATIENT_SUBMIT_REVIEW_URL)
    public String patientSubmitReviewPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {

        DoctorDto doctorDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        model.addAttribute("doctorFullName", doctorDto.getFullName());
        return PATIENT_SUBMIT_REVIEW_FILE;
    }
}
