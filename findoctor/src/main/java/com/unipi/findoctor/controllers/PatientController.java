package com.unipi.findoctor.controllers;

import com.unipi.findoctor.constants.ControllerConstants;
import com.unipi.findoctor.dto.*;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.security.SecurityUtil;
import com.unipi.findoctor.services.AppointmentService;
import com.unipi.findoctor.services.DoctorService;
import com.unipi.findoctor.security.SecurityUtil;
import com.unipi.findoctor.services.PatientService;
import com.unipi.findoctor.services.RatingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@AllArgsConstructor
@Controller
public class PatientController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final SecurityUtil securityUtil;
//    private final RatingService ratingService;
    private final AppointmentService appointmentService;

    @GetMapping({PATIENT_ROOT_URL, PATIENT_INDEX_URL_1})
    public String patientIndexPage(Model model) {

        PatientDto patientDto = securityUtil.getSessionPatient();

        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
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

        PatientDto patientDto = securityUtil.getSessionPatient();
        String loggedInUserType = patientDto != null ? USER_TYPE_PATIENT : USER_TYPE_VISITOR;

        DoctorDetailsDto doctorDetailsDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDetailsDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        int views = doctorService.getDoctorViews(doctorUsername);

        model.addAttribute("doctorDetails", doctorDetailsDto);
        model.addAttribute("loggedInUserType", loggedInUserType);
        model.addAttribute("views", views);
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        return PATIENT_DETAIL_PAGE_FILE;
    }

    @GetMapping(PATIENT_FAQ_URL)
    public String patientFaqPage() {
        return PATIENT_FAQ_FILE;
    }

    @GetMapping(PATIENT_GRID_LIST_URL)
    public String patientGridListPage(@RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false) String q , Model model) {

        int adjustedPageNumber;

        try {
            adjustedPageNumber = Integer.parseInt(page) - 1;
        }
        catch (NumberFormatException e) {
            return "error/404";
        }

        if (adjustedPageNumber < 0) {
            return "error/404";
        }

        Page<DoctorDetailsDto> doctorDetailsDtoPage = doctorService.getDoctorsByPage(q, adjustedPageNumber, 9);

        model.addAttribute("doctorDetails", doctorDetailsDtoPage.getContent());
        model.addAttribute("doctorCount", doctorDetailsDtoPage.getNumberOfElements());
        model.addAttribute("currentPage", adjustedPageNumber + 1);
        model.addAttribute("totalPages", doctorDetailsDtoPage.getTotalPages());
        model.addAttribute("totalDoctors", doctorDetailsDtoPage.getTotalElements());
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        return PATIENT_GRID_LIST_FILE;
    }

    @GetMapping(PATIENT_SUBMIT_REVIEW_URL)
    public String patientSubmitReviewPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {

        DoctorDetailsDto doctorDetailsDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDetailsDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        PatientDto patientDto = securityUtil.getSessionPatient();
        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to leave a review");
        }

        RatingDto ratingDto = RatingDto.builder().build();

        model.addAttribute("doctorFullName", doctorDetailsDto.getFullName());
        model.addAttribute("ratingDto", ratingDto);
        return PATIENT_SUBMIT_REVIEW_FILE;
    }

    @PostMapping(PATIENT_SUBMIT_REVIEW_POST_URL)
    public String patientSubmitReviewEndpoint(@ModelAttribute RatingDto ratingDto){

        // TODO: Check if value inputs are not changed

        //ratingService.saveRating(ratingDto);

        return "redirect:/";
    }

    @PostMapping("/appointment/new")
    public String newAppointment(@RequestParam("selectedDate") String selectedDate,
                                 @RequestParam("doctorUsername") String doctorUsername,
                                 @RequestParam("timeslot") String timeslot,
                                 RedirectAttributes redirectAttributes){

        PatientDto loggedInPatient = securityUtil.getSessionPatient();

        if (loggedInPatient == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        LocalDate parsedDate;
        LocalTime parsedTime;

        try {
            parsedDate = LocalDate.parse(selectedDate);
            parsedTime = LocalTime.parse(timeslot);

        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        AppointmentDto appointmentToSave = AppointmentDto.builder()
                .patient(patientService.findPatientByUsername(loggedInPatient.getUser().getUsername()))
                .doctor(doctorService.findDoctor(doctorUsername))
                .date(parsedDate)
                .time_slot(parsedTime)
                        .build();

        AppointmentDto appointmentDto = appointmentService.saveAppointment(appointmentToSave);
        redirectAttributes.addFlashAttribute("status", "new-booking");
        redirectAttributes.addFlashAttribute("appointmentId", appointmentDto.getId());
        return "redirect:/confirmation";
    }


    @GetMapping("/confirmation")
    public String showSuccessPage(@ModelAttribute("status") String status) {

        if (status.isBlank() || status == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return PATIENT_CONFIRMATION_FILE;
    }

}
