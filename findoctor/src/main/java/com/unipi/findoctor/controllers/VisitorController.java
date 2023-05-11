package com.unipi.findoctor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@Controller
public class VisitorController {

    @GetMapping({ROOT_URL, INDEX_URL})
    public String indexPage() {
        return INDEX_FILE;
    }
}
