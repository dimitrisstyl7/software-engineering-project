package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.UserDto;
import com.unipi.findoctor.security.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.unipi.findoctor.constants.ControllerConstants.*;

@AllArgsConstructor
@Controller
public class VisitorController {
    @GetMapping({ROOT_URL, INDEX_URL})
    public String indexPage() {
        UserDto user = SecurityUtil.getSessionUser();

        if (user != null) {
            System.out.println(user.getUsername());
            System.out.println(user.getUserType());
        }

        return INDEX_FILE;
    }

    @GetMapping(ABOUT_URL)
    public String aboutPage() {
        return ABOUT_FILE;
    }

    @GetMapping(BOOKING_URL)
    public String bookingPage() {
        return BOOKING_FILE;
    }

    @GetMapping(CONTACT_US_URL)
    public String contactUsPage() {
        return CONTACT_US_FILE;
    }

    @GetMapping(DETAIL_PAGE_URL)
    public String detailPage() {
        return DETAIL_PAGE_FILE;
    }

    @GetMapping(FAQ_URL)
    public String faqPage() {
        return FAQ_FILE;
    }

    @GetMapping(GRID_LIST_URL)
    public String gridListPage() {
        return GRID_LIST_FILE;
    }

    @GetMapping(ICON_PACK_1_URL)
    public String iconPack1Page() {
        return ICON_PACK_1_FILE;
    }

    @GetMapping(ICON_PACK_2_URL)
    public String iconPack2Page() {
        return ICON_PACK_2_FILE;
    }

    @GetMapping(ICON_PACK_3_URL)
    public String iconPack3Page() {
        return ICON_PACK_3_FILE;
    }

    @GetMapping(SUBMIT_REVIEW_URL)
    public String submitReviewPage() {
        return SUBMIT_REVIEW_FILE;
    }
}
