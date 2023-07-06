package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.*;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.mappers.PatientMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.security.SecurityUtil;
import com.unipi.findoctor.services.*;
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

import static com.unipi.findoctor.constants.ControllerConstants.*;

@AllArgsConstructor
@Controller
public class PatientController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final RatingService ratingService;
    private final SecurityUtil securityUtil;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;
    private final UserService userService;


    @GetMapping({PATIENT_ROOT_URL, PATIENT_INDEX_URL_1})
    public String patientIndexPage(Model model) {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            return securityUtil.redirectBasedOnUserRole(authDto.getUserType());
        }

        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        model.addAttribute("contactDetails", userService.getAdminDetails());
        return PATIENT_INDEX_FILE;
    }

    @GetMapping(PATIENT_PROFILE_URL)
    public String patientProfilePage(Model model) {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            return securityUtil.redirectBasedOnUserRole(authDto.getUserType());
        }

        PatientDto patientDto = securityUtil.getSessionPatient();
        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("patient", patientDto);
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        model.addAttribute("contactDetails", userService.getAdminDetails());
        return PATIENT_PROFILE_FILE;
    }

    @PostMapping(PATIENT_PROFILE_URL)
    public String patientProfileUpdate(@ModelAttribute("patient") PatientDto patientDto) {
        Patient patient = patientMapper.mapToPatient(patientDto);
        patientService.updatePatient(patient);
        return "redirect:" + PATIENT_PROFILE_FILE;
    }

    @GetMapping(PATIENT_DETAIL_URL)
    public String patientDetailPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            return securityUtil.redirectBasedOnUserRole(authDto.getUserType());
        }

        PatientDto patientDto = securityUtil.getSessionPatient();
        String loggedInUserType = patientDto != null ? USER_TYPE_PATIENT : USER_TYPE_VISITOR;

        DoctorDto doctorDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        boolean hasReviewed;
        if (loggedInUserType.equals(USER_TYPE_PATIENT)) {
            hasReviewed = ratingService.patientHasReviewed(patientMapper.mapToPatient(patientDto), doctorMapper.mapToDoctor(doctorDto));
        } else {  // USER_TYPE_VISITOR
            hasReviewed = false;
        }

        model.addAttribute("doctorDetails", doctorDto);
        model.addAttribute("loggedInUserType", loggedInUserType);
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        model.addAttribute("contactDetails", userService.getAdminDetails());
        model.addAttribute("hasReviewed", hasReviewed);
        return PATIENT_DETAIL_PAGE_FILE;
    }

    @GetMapping(PATIENT_GRID_LIST_URL)
    public String patientGridListPage(@RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false) String q, Model model) {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            return securityUtil.redirectBasedOnUserRole(authDto.getUserType());
        }

        int adjustedPageNumber;

        try {
            adjustedPageNumber = Integer.parseInt(page) - 1;
        } catch (NumberFormatException e) {
            return "error/404";
        }

        if (adjustedPageNumber < 0) {
            return "error/404";
        }

        Page<DoctorDto> doctorDetailsDtoPage = doctorService.getDoctorsByPage(q, adjustedPageNumber, 9);

        model.addAttribute("doctorDetails", doctorDetailsDtoPage.getContent());
        model.addAttribute("doctorCount", doctorDetailsDtoPage.getNumberOfElements());
        model.addAttribute("currentPage", adjustedPageNumber + 1);
        model.addAttribute("totalPages", doctorDetailsDtoPage.getTotalPages());
        model.addAttribute("totalDoctors", doctorDetailsDtoPage.getTotalElements());
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        model.addAttribute("contactDetails", userService.getAdminDetails());
        return PATIENT_GRID_LIST_FILE;
    }

    @GetMapping(PATIENT_GET_SUBMIT_REVIEW_URL)
    public String patientSubmitReviewPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            return securityUtil.redirectBasedOnUserRole(authDto.getUserType());
        }

        PatientDto patientDto = securityUtil.getSessionPatient();
        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You are not authorized to leave a review");
        }

        DoctorDto doctorDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        if (ratingService.patientHasReviewed(patientMapper.mapToPatient(patientDto), doctorMapper.mapToDoctor(doctorDto))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        RatingDto ratingDto = RatingDto.builder()
                .doctorDto(doctorDto)
                .build();

        model.addAttribute("doctorFullName", doctorDto.getFullName());
        model.addAttribute("ratingDto", ratingDto);
        model.addAttribute("doctorUsername", doctorDto.getUserDto().getUsername());
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        model.addAttribute("contactDetails", userService.getAdminDetails());
        return PATIENT_SUBMIT_REVIEW_FILE;
    }

    @GetMapping(PATIENT_GET_EDIT_REVIEW_URL)
    public String patientEditReviewPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            return securityUtil.redirectBasedOnUserRole(authDto.getUserType());
        }

        PatientDto patientDto = securityUtil.getSessionPatient();
        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You are not authorized to edit your review");
        }

        DoctorDto doctorDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        if (!ratingService.patientHasReviewed(patientMapper.mapToPatient(patientDto), doctorMapper.mapToDoctor(doctorDto))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        RatingDto ratingDto = ratingService.findRating(patientMapper.mapToPatient(patientDto), doctorMapper.mapToDoctor(doctorDto));

        model.addAttribute("doctorFullName", doctorDto.getFullName());
        model.addAttribute("doctorUsername", doctorDto.getUserDto().getUsername());
        model.addAttribute("ratingDto", ratingDto);
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        model.addAttribute("contactDetails", userService.getAdminDetails());
        return PATIENT_EDIT_REVIEW_FILE;
    }

    @PostMapping(PATIENT_POST_SUBMIT_REVIEW_URL)
    public String patientSubmitReviewEndpoint(@ModelAttribute RatingDto ratingDto, RedirectAttributes redirectAttributes) {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            return securityUtil.redirectBasedOnUserRole(authDto.getUserType());
        }

        PatientDto patientDto = securityUtil.getSessionPatient();

        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to leave a review");
        }

        Patient patient = patientMapper.mapToPatient(patientDto);
        Doctor doctor = doctorMapper.mapToDoctor(ratingDto.getDoctorDto());

        if (ratingService.patientHasReviewed(patient, doctor)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (ratingDto.getRatingValue() < 1 || ratingDto.getRatingValue() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ratingDto.getReview() == null || ratingDto.getReview().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        ratingDto.setPatientDto(patientDto);
        ratingService.saveRating(ratingDto);

        redirectAttributes.addFlashAttribute("title", "Success!");
        redirectAttributes.addFlashAttribute("message", "Your review has been created.");
        return "redirect:/details/" + ratingDto.getDoctorDto().getUserDto().getUsername();
    }

    @PostMapping(PATIENT_PUT_REVIEW_URL)
    public String patientPutReviewEndpoint(@ModelAttribute("ratingDto") RatingDto ratingDto, RedirectAttributes redirectAttributes) {
        PatientDto patientDto = securityUtil.getSessionPatient();
        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to leave a review");
        }

        Patient patient = patientMapper.mapToPatient(patientDto);
        Doctor doctor = doctorMapper.mapToDoctor(ratingDto.getDoctorDto());

        if (!ratingService.patientHasReviewed(patient, doctor)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (ratingDto.getRatingValue() < 1 || ratingDto.getRatingValue() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ratingDto.getReview() == null || ratingDto.getReview().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        ratingDto.setPatientDto(patientDto);
        ratingService.updateRating(ratingDto);

        redirectAttributes.addFlashAttribute("title", "Success!");
        redirectAttributes.addFlashAttribute("message", "Your review has been updated.");
        return "redirect:/details/" + ratingDto.getDoctorDto().getUserDto().getUsername();
    }

    @GetMapping(PATIENT_DELETE_REVIEW_URL)
    public String patientDeleteReviewEndpoint(@PathVariable("doctorUsername") String doctorUsername, RedirectAttributes redirectAttributes) {
        PatientDto patientDto = securityUtil.getSessionPatient();

        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to leave a review");
        }

        if (doctorUsername == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        DoctorDto doctorDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        RatingDto ratingDto = ratingService.findRating(patientMapper.mapToPatient(patientDto), doctorMapper.mapToDoctor(doctorDto));
        if (ratingDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        ratingService.deleteRating(ratingDto);

        redirectAttributes.addFlashAttribute("title", "Success!");
        redirectAttributes.addFlashAttribute("message", "Your review has been deleted.");
        return "redirect:/details/" + doctorUsername;
    }

    @PostMapping(PATIENT_NEW_APPOINTMENT_POST_URL)
    public String newAppointment(@RequestParam("selectedDate") String selectedDate,
                                 @RequestParam("doctorUsername") String doctorUsername,
                                 @RequestParam("timeslot") String timeslot,
                                 RedirectAttributes redirectAttributes) {
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

        Patient patient = patientService.findPatientByUsername(loggedInPatient.getUserDto().getUsername());
        Doctor doctor = doctorService.findDoctor(doctorUsername);

        AppointmentDto appointmentToSave = AppointmentDto.builder()
                .patientDto(patientMapper.mapToPatientDto(patient))
                .doctorDto(doctorMapper.mapToDoctorDto(doctor))
                .date(parsedDate)
                .timeSlot(parsedTime)
                .build();

        AppointmentDto appointmentDto = appointmentService.saveAppointment(appointmentToSave);
        redirectAttributes.addFlashAttribute("status", "new-booking");
        redirectAttributes.addFlashAttribute("appointmentId", appointmentDto.getId());
        return "redirect:" + APPOINTMENT_CONFIRMATION_URL;
    }


    @GetMapping(APPOINTMENT_CONFIRMATION_URL)
    public String showSuccessPage(@ModelAttribute("status") String status, Model model) {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            return securityUtil.redirectBasedOnUserRole(authDto.getUserType());
        }

        if (status.isBlank() || status == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        model.addAttribute("contactDetails", userService.getAdminDetails());
        return APPOINTMENT_CONFIRMATION_FILE;
    }

}
