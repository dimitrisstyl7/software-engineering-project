package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.RegistrationDto;
import com.unipi.findoctor.mappers.UserMapper;
import com.unipi.findoctor.models.User;
import com.unipi.findoctor.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@AllArgsConstructor
@Controller
public class AuthController {
    private final UserService userService;

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
    public String registerSave(@Valid @ModelAttribute("user") RegistrationDto registrationDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", registrationDto);
            return REGISTER_FILE;
        }

        User existingUser = userService.findByUsername(registrationDto.getUsername());
        if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
            return "redirect:" + REGISTER_URL + "?fail";
        }

        User user = UserMapper.mapToUser(registrationDto);
        userService.saveUser(user);
        return "redirect:" + CONFIRMATION_URL;
    }


    @GetMapping(CONFIRMATION_URL)
    public String confirmationPage() {
        return CONFIRMATION_FILE;
    }
}
