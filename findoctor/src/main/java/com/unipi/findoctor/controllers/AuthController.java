package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.RegistrationDto;
import com.unipi.findoctor.mappers.AuthMapper;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.models.User;
import com.unipi.findoctor.security.SecurityUtil;
import com.unipi.findoctor.services.DoctorService;
import com.unipi.findoctor.services.PatientService;
import com.unipi.findoctor.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@AllArgsConstructor
@Controller
public class AuthController {
    private final UserService userService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final SecurityUtil securityUtil;

    @GetMapping(LOGIN_URL)
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) { // if logged in
            String role = auth.getAuthorities().iterator().next().getAuthority(); // get logged in user's role
            switch (role) { // redirect to the correct page based on the role
                case "admin" -> {
                    return "redirect:" + ADMIN_ROOT_URL;
                }
                case "patient" -> {
                    return "redirect:" + PATIENT_ROOT_URL;
                }
                case "doctor" -> {
                    return "redirect:" + DOCTOR_ROOT_URL;
                }
            }
        }
        return LOGIN_FILE; // if not logged in, return login page
    }

    @GetMapping(REGISTER_URL)
    public String registerPage(Model model) {
        if (securityUtil.getSessionUser() != null) { // if logged in, redirect to home page
            return securityUtil.redirectBasedOnUserRole();
        }
        model.addAttribute("user", new RegistrationDto());
        return REGISTER_FILE;
    }

    @PostMapping(REGISTER_URL)
    public String registerSave(@Valid @ModelAttribute("user") RegistrationDto registrationDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            registrationDto.setIsDoctor(false); // reset isDoctor to false (reset radio button)
            model.addAttribute("user", registrationDto);
            return REGISTER_FILE;
        }

        if (registrationDto.getIsDoctor()) { // is doctor
            Doctor existingDoctor = doctorService.findByAfm(registrationDto.getAfm());
            if (existingDoctor != null && existingDoctor.getAfm() != null && !existingDoctor.getAfm().isEmpty()) {
                result.rejectValue("afm", "afm.alreadyexists",
                        "There is already an account registered with this AFM.");
                registrationDto.setIsDoctor(false);
                model.addAttribute("user", registrationDto);
                return REGISTER_FILE;
            }
        } else { // is patient
            Patient existingPatient = patientService.findByAmka(registrationDto.getAmka());
            if (existingPatient != null && existingPatient.getAmka() != null && !existingPatient.getAmka().isEmpty()) {
                result.rejectValue("amka", "amka.alreadyexists",
                        "There is already an account registered with this AMKA.");
                registrationDto.setIsDoctor(false);
                model.addAttribute("user", registrationDto);
                return REGISTER_FILE;
            }
        }

        User existingUser = userService.findByUsername(registrationDto.getUsername());
        if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
            result.rejectValue("username", "username.alreadyexists",
                    "There is already an account registered with this username.");
            registrationDto.setIsDoctor(false);
            model.addAttribute("user", registrationDto);
            return REGISTER_FILE;
        }

        if (registrationDto.getIsDoctor()) {
            Doctor doctor = AuthMapper.mapToDoctor(registrationDto);
            doctorService.saveDoctor(doctor);
        } else {
            Patient patient = AuthMapper.mapToPatient(registrationDto);
            patientService.savePatient(patient);
        }

        return "redirect:" + CONFIRMATION_URL + "/" + registrationDto.getIsDoctor();
    }

    @GetMapping(CONFIRMATION_URL + "/{isDoctor}")
    public String confirmationPage(@PathVariable("isDoctor") boolean isDoctor, Model model) {
        model.addAttribute("isDoctor", isDoctor);
        return CONFIRMATION_FILE;
    }
}
