package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.RegistrationDto;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@NoArgsConstructor
@Controller
public class AuthController {
    @GetMapping(LOGIN_URL)
    public String loginPage() {
        return LOGIN_FILE;
    }

    @GetMapping(REGISTER_URL)
    public String registerPage(Model model) {
        model.addAttribute("user", new RegistrationDto());
        return REGISTER_FILE;
    }

    @PostMapping(REGISTER_URL)
    public String registerSave(@Valid @ModelAttribute("user") RegistrationDto user,
                               @RequestParam("terms_check_box") boolean isDoctor, BindingResult result, Model model) {
        // TODO: if flag is checked, means that user is a doctor.
        return REGISTER_FILE;
    }

    @GetMapping(CONFIRMATION_URL)
    public String confirmationPage() {
        return CONFIRMATION_FILE;
    }
}
