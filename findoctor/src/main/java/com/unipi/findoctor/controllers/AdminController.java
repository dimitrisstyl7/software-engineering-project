package com.unipi.findoctor.controllers;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.mappers.DoctorMapper;
import com.unipi.findoctor.services.AdminService;
import com.unipi.findoctor.services.DoctorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.unipi.findoctor.constants.ControllerConstants.*;


@AllArgsConstructor
@Controller
public class AdminController {
    private AdminService adminService;
    private DoctorService doctorService;
    private DoctorMapper drmapper;
    @GetMapping({ADMIN_ROOT_URL, ADMIN_INDEX_URL_1, ADMIN_INDEX_URL_2})
    public String adminIndexPage() {
        return ADMIN_INDEX_FILE;
    }

    @GetMapping(ADMIN_ADD_LISTING_URL)
    public String adminAddListingPage() {
        return ADMIN_ADD_LISTING_FILE;
    }

    @GetMapping(ADMIN_BOOKINGS_URL)
    public String adminBookingsPage(Model model) {
        List<DoctorDto> doctors = adminService.findAllDoctors();
        model.addAttribute("doctors", doctors);
        return ADMIN_BOOKINGS_FILE;
    }
    @GetMapping(ADMIN_PATIENTS_URL)
    public String viewPatients(Model model) {
        List<PatientDto> patients = adminService.findAllPatients();
        model.addAttribute("patients", patients);
        return ADMIN_PATIENTS_FILE;
    }
    @GetMapping("/admin/{Afm}/approval")
    public String approveDoctor(@PathVariable("Afm") String Afm){

        DoctorDto doctor = adminService.findDoctorByAfm(Afm);
        if (doctor == null) throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (doctor.getStatus().equals("pending")) {
            doctor.setStatus("approved");
            doctorService.updateDoctor(drmapper.mapToDoctor(doctor));
        }
        return "redirect:/admin/bookings";
    }
    @GetMapping("/admin/{Afm}/cancelled")
    public String cancelDoctor(@PathVariable("Afm") String Afm){

        DoctorDto doctor = adminService.findDoctorByAfm(Afm);
        if (doctor == null) throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (doctor.getStatus().equals("pending")) {
            doctor.setStatus("cancelled");
            doctorService.updateDoctor(drmapper.mapToDoctor(doctor));
        }
        return "redirect:/admin/bookings";
    }

    @GetMapping(ADMIN_VIEW_URL)
    public String adminViewPage(Model model) {
        List<DoctorDto> doctors = adminService.findAllDoctors();
        model.addAttribute("doctors", doctors);
        return ADMIN_VIEW_FILE;
    }

    @GetMapping(ADMIN_CHARTS_URL)
    public String adminChartsPage() {
        return ADMIN_CHARTS_FILE;
    }

    @GetMapping(ADMIN_DOCTOR_PROFILE_URL)
    public String adminDoctorProfilePage() {
        return ADMIN_DOCTOR_PROFILE_FILE;
    }

    @GetMapping(ADMIN_MESSAGES_URL)
    public String adminMessagesPage() {
        return ADMIN_MESSAGES_FILE;
    }

    @GetMapping(ADMIN_PATIENT_PROFILE_URL)
    public String adminPatientProfilePage() {
        return ADMIN_PATIENT_PROFILE_FILE;
    }

    @GetMapping(ADMIN_REVIEWS_URL)
    public String adminReviewsPage() {
        return ADMIN_REVIEWS_FILE;
    }

    @GetMapping(ADMIN_TABLES_URL)
    public String adminTablesPage() {
        return ADMIN_TABLES_FILE;
    }



}
