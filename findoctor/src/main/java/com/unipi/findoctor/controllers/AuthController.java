package com.unipi.findoctor.controllers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@NoArgsConstructor
@Controller
public class AuthController {
    @GetMapping(LOGIN_URL)
    public String loginPage() {
        return LOGIN_FILE;
    }

    @GetMapping(REGISTER_URL)
    public String registerPage() {
        return REGISTER_FILE;
    }

    @GetMapping(REGISTER_2_URL)
    public String register2Page() {
        return REGISTER_2_FILE;
    }

    @GetMapping(CONFIRMATION_URL)
    public String confirmationPage() {
        return CONFIRMATION_FILE;
    }
}
