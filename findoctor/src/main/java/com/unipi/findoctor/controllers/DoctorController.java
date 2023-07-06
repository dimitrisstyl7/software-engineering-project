package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.repositories.DoctorRepository;
import com.unipi.findoctor.security.SecurityUtil;
import com.unipi.findoctor.services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@AllArgsConstructor
@Controller
public class DoctorController {
    private final SecurityUtil securityUtil;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;

    @GetMapping({DOCTOR_ROOT_URL, DOCTOR_INDEX_URL_1, DOCTOR_INDEX_URL_2})
    public String doctorIndexPage() {
        return DOCTOR_INDEX_FILE;
    }

    @GetMapping(DOCTOR_BOOKINGS_URL)
    public String doctorBookingsPage(Model model) {
        model.addAttribute("doctorUsername", securityUtil.getSessionUser().getUsername());
        return DOCTOR_BOOKINGS_FILE;
    }

    @GetMapping(DOCTOR_PROFILE_URL)
    public String doctorDoctorProfilePage(Model model) {
        String username = securityUtil.getSessionUser().getUsername();
        Doctor doctor = doctorRepository.findByUser_username(username);
        if (doctor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        DoctorDto doctorDto = doctorMapper.mapToDoctorDto(doctor);
        model.addAttribute("doctor", doctorDto);
        return DOCTOR_PROFILE_FILE;
    }

    @PostMapping(DOCTOR_PROFILE_URL)
    public String doctorProfileUpdate(@ModelAttribute("user") DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.mapToDoctor(doctorDto);
        doctorService.updateDoctor(doctor);
        return "redirect:" + DOCTOR_PROFILE_URL;
    }

    @GetMapping(DOCTOR_REVIEWS_URL)
    public String doctorReviewsPage(Model model) {
        String username = securityUtil.getSessionUser().getUsername();
        Doctor doctor = doctorRepository.findByUser_username(username);
        if (doctor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("allReviews", doctor.getRatings());
        return DOCTOR_REVIEWS_FILE;
    }
}
