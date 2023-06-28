package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.UserDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.security.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@AllArgsConstructor
@Controller
public class DoctorController {
    private final SecurityUtil securityUtil;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @GetMapping({DOCTOR_ROOT_URL, DOCTOR_INDEX_URL_1, DOCTOR_INDEX_URL_2})
    public String doctorIndexPage() {
        UserDto Userdto = securityUtil.getSessionUser();
        Userdto.getUsername();
        return DOCTOR_INDEX_FILE;
    }

    @GetMapping(DOCTOR_ADD_LISTING_URL)
    public String doctorAddListingPage() {
        return DOCTOR_ADD_LISTING_FILE;
    }

    @GetMapping(DOCTOR_BOOKINGS_URL)
    public String doctorBookingsPage(Model model) {
        model.addAttribute("doctorUsername",securityUtil.getSessionUser().getUsername());
        return DOCTOR_BOOKINGS_FILE;
    }

    @GetMapping(DOCTOR_CHARTS_URL)
    public String doctorChartsPage() {
        return DOCTOR_CHARTS_FILE;
    }

    @GetMapping(DOCTOR_DOCTOR_PROFILE_URL)
    public String doctorDoctorProfilePage(Model model) {
        String username = securityUtil.getSessionUser().getUsername();
        Doctor doctor = doctorRepository.findByUser_username(username);
        if (doctor == null){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        DoctorDto doctorDto = doctorMapper.mapToDoctorDto(doctor);
        model.addAttribute("doctor",doctorDto);

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
