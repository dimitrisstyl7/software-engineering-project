package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.models.User;
import com.unipi.findoctor.security.SecurityUtil;
import com.unipi.findoctor.services.AdminService;
import com.unipi.findoctor.services.DoctorService;
import com.unipi.findoctor.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.unipi.findoctor.constants.ControllerConstants.*;


@AllArgsConstructor
@Controller
public class AdminController {
    private AdminService adminService;
    private DoctorService doctorService;
    private DoctorMapper doctorMapper;
    private SecurityUtil securityUtil;
    private UserService userService;

    @GetMapping({ADMIN_ROOT_URL, ADMIN_INDEX_URL_1, ADMIN_INDEX_URL_2})
    public String adminIndexPage() {
        return ADMIN_INDEX_FILE;
    }

    @GetMapping(ADMIN_VALIDATIONS_URL)
    public String adminBookingsPage(Model model) {
        List<DoctorDto> doctors = adminService.findAllDoctors();
        model.addAttribute("doctors", doctors);
        return ADMIN_VALIDATIONS_FILE;
    }

    @GetMapping(ADMIN_PATIENTS_URL)
    public String viewPatients(Model model) {
        List<PatientDto> patients = adminService.findAllPatients();
        model.addAttribute("patients", patients);
        return ADMIN_PATIENTS_FILE;
    }

    @GetMapping("/admin/{Afm}/approval")
    public String approveDoctor(@PathVariable("Afm") String Afm) {
        DoctorDto doctor = adminService.findDoctorByAfm(Afm);
        if (doctor == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (doctor.getStatus().equals("pending")) {
            doctor.setStatus("approved");
            doctorService.updateDoctorStatus(doctorMapper.mapToDoctor(doctor));
        }
        return "redirect:" + ADMIN_VALIDATIONS_URL;
    }

    @GetMapping("/admin/{Afm}/cancelled")
    public String cancelDoctor(@PathVariable("Afm") String Afm) {
        DoctorDto doctor = adminService.findDoctorByAfm(Afm);
        if (doctor == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (doctor.getStatus().equals("pending")) {
            doctor.setStatus("cancelled");
            doctorService.updateDoctorStatus(doctorMapper.mapToDoctor(doctor));
        }
        return "redirect:" + ADMIN_VALIDATIONS_URL;
    }

    @GetMapping(ADMIN_VIEW_URL)
    public String adminViewPage(Model model) {
        List<DoctorDto> doctors = adminService.findAllDoctors();
        model.addAttribute("doctors", doctors);
        return ADMIN_VIEW_FILE;
    }

    @GetMapping(ADMIN_PROFILE_URL)
    public String updateProfile(Model model) {
        String username;
        username = securityUtil.getSessionUser().getUsername();
        User user1 = userService.findByUsername(username);
        model.addAttribute("user", User.builder().username(username)
                .email(user1.getEmail())
                .phone(user1.getPhone())
                .build());
        return ADMIN_PROFILE_FILE;
    }

    @PostMapping(ADMIN_PROFILE_URL)
    public String updateProfilePost(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:" + ADMIN_PROFILE_URL;
    }

    @GetMapping(ADMIN_BANNED_DOCTORS_URL)
    public String adminTablesPage() {
        return ADMIN_BANNED_DOCTORS_FILE;
    }
}
