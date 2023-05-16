package com.unipi.findoctor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

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
}
