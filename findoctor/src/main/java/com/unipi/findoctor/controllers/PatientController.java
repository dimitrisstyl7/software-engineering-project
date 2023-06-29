package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.*;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.mappers.PatientMapper;
import com.unipi.findoctor.security.SecurityUtil;
import com.unipi.findoctor.services.AppointmentService;
import com.unipi.findoctor.services.DoctorService;
import com.unipi.findoctor.services.PatientService;
import com.unipi.findoctor.services.RatingService;
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

    private void checkAccess() {
        AuthDto authDto = securityUtil.getSessionUser();
        if (authDto != null && !authDto.getUserType().equals(USER_TYPE_PATIENT)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping({PATIENT_ROOT_URL, PATIENT_INDEX_URL_1})
    public String patientIndexPage(Model model) {
        checkAccess();

        PatientDto patientDto = securityUtil.getSessionPatient();

        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        return PATIENT_INDEX_FILE;
    }

    @GetMapping(PATIENT_PROFILE_PAGE_URL)
    public String patientProfilePage(Model model) {
        checkAccess();

        PatientDto patientDto = securityUtil.getSessionPatient();
        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        model.addAttribute("patient", patientDto);
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        return PATIENT_PROFILE_FILE;
    }

    @GetMapping(PATIENT_DETAIL_PAGE_URL)
    public String patientDetailPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {
        checkAccess();

        PatientDto patientDto = securityUtil.getSessionPatient();
        String loggedInUserType = patientDto != null ? USER_TYPE_PATIENT : USER_TYPE_VISITOR;

        DoctorDto doctorDto = doctorService.getDoctorDetailsByUsername(doctorUsername);
        if (doctorDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        boolean hasReviewed;
        if (loggedInUserType.equals(USER_TYPE_PATIENT)) {
            hasReviewed = ratingService.patientHasReviewed(patientMapper.mapToPatient(patientDto), doctorMapper.mapToDoctor(doctorDto));
        } else if (loggedInUserType.equals(USER_TYPE_VISITOR)) {
            hasReviewed = false;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }


        model.addAttribute("doctorDetails", doctorDto);
        model.addAttribute("loggedInUserType", loggedInUserType);
        model.addAttribute("isLoggedIn", securityUtil.isPatientLoggedIn());
        model.addAttribute("hasReviewed", hasReviewed);
        return PATIENT_DETAIL_PAGE_FILE;
    }

    @GetMapping(PATIENT_GRID_LIST_URL)
    public String patientGridListPage(@RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false) String q, Model model) {
        checkAccess();

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
        return PATIENT_GRID_LIST_FILE;
    }

    @GetMapping(PATIENT_GET_SUBMIT_REVIEW_URL)
    public String patientSubmitReviewPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {
        checkAccess();

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
                .doctor(doctorMapper.mapToDoctor(doctorDto))
                .build();

        model.addAttribute("doctorFullName", doctorDto.getFullName());
        model.addAttribute("ratingDto", ratingDto);
        model.addAttribute("doctorUsername", doctorDto.getUser().getUsername());
        return PATIENT_SUBMIT_REVIEW_FILE;
    }

    @GetMapping(PATIENT_GET_EDIT_REVIEW_URL)
    public String patientEditReviewPage(@PathVariable("doctorUsername") String doctorUsername, Model model) {
        // TODO: Fix duplicate code
        checkAccess();

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
        model.addAttribute("doctorUsername", doctorDto.getUser().getUsername());
        model.addAttribute("ratingDto", ratingDto);
        return PATIENT_EDIT_REVIEW_FILE;
    }

    @PostMapping(PATIENT_POST_SUBMIT_REVIEW_URL)
    public String patientSubmitReviewEndpoint(@ModelAttribute RatingDto ratingDto, RedirectAttributes redirectAttributes) {
        checkAccess();

        PatientDto patientDto = securityUtil.getSessionPatient();

        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to leave a review");
        }

        if (ratingService.patientHasReviewed(patientMapper.mapToPatient(patientDto), ratingDto.getDoctor())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (ratingDto.getRatingValue() < 1 || ratingDto.getRatingValue() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ratingDto.getReview() == null || ratingDto.getReview().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        ratingDto.setPatient(patientMapper.mapToPatient(patientDto));
        RatingDto returned_rating = ratingService.saveRating(ratingDto);

        redirectAttributes.addFlashAttribute("title", "Success!");
        redirectAttributes.addFlashAttribute("message", "Your review has been created.");
        return "redirect:/details/" + ratingDto.getDoctor().getUser().getUsername();
    }

    @GetMapping(PATIENT_PUT_REVIEW_URL)
    public String patientPutReviewEndpoint(@ModelAttribute RatingDto ratingDto, RedirectAttributes redirectAttributes) {
        checkAccess();

        PatientDto patientDto = securityUtil.getSessionPatient();

        if (patientDto == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to leave a review");
        }

        if (!ratingService.patientHasReviewed(patientMapper.mapToPatient(patientDto), ratingDto.getDoctor())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (ratingDto.getRatingValue() < 1 || ratingDto.getRatingValue() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ratingDto.getReview() == null || ratingDto.getReview().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        ratingDto.setPatient(patientMapper.mapToPatient(patientDto));
        RatingDto returned_rating = ratingService.updateRating(ratingDto);

        redirectAttributes.addFlashAttribute("title", "Success!");
        redirectAttributes.addFlashAttribute("message", "Your review has been updated.");
        return "redirect:/details/" + ratingDto.getDoctor().getUser().getUsername();
    }

    @GetMapping(PATIENT_DELETE_REVIEW_URL)
    public String patientDeleteReviewEndpoint(@PathVariable("doctorUsername") String doctorUsername, RedirectAttributes redirectAttributes) {
        checkAccess();

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
        checkAccess();

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
        checkAccess();

        if (status.isBlank() || status == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return PATIENT_CONFIRMATION_FILE;
    }

}
