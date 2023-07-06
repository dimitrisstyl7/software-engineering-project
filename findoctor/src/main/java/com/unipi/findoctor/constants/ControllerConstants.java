package com.unipi.findoctor.constants;

import java.util.ArrayList;

public class ControllerConstants {

    /* ---------------------------- DOCTOR SPECIALIZATION LIST ------------------------------------ */
    public static final ArrayList<String> DOCTOR_SPECIALIZATION_LIST = new ArrayList<>() {{
        add("Allergist");
        add("Anesthesiologist");
        add("Cardiologist");
        add("Dentist");
        add("Dermatologist");
        add("Endocrinologist");
        add("ENT Specialist");
        add("Epidemiologist");
        add("Gynecologist");
        add("Immunologist");
        add("Infectious Disease Specialist");
        add("Internal Medicine Specialist");
        add("Medical Geneticist");
        add("Microbiologist");
        add("Neonatologist");
        add("Neurologist");
        add("Neurosurgeon");
        add("Obstetrician");
        add("Oncologist");
        add("Orthopedic Surgeon");
        add("Pediatrician");
        add("Physiologist");
        add("Plastic Surgeon");
        add("Podiatrist");
        add("Psychiatrist");
        add("Radiologist");
        add("Rheumatologist");
        add("Surgeon");
        add("Urologist");
    }};


    /* ---------------------------- USER TYPES ------------------------------------ */
    public static final String USER_TYPE_VISITOR = "visitor";
    public static final String USER_TYPE_PATIENT = "patient";
    public static final String USER_TYPE_DOCTOR = "doctor";
    public static final String USER_TYPE_ADMIN = "admin";


    /* ---------------------------- URLs ------------------------------------ */
    /* PATIENT URLs */
    public static final String PATIENT_ROOT_URL = "/";
    public static final String PATIENT_INDEX_URL_1 = "/index";
    public static final String PATIENT_PROFILE_URL = "/profile";
    public static final String PATIENT_DETAIL_URL = "/details/{doctorUsername}";
    public static final String PATIENT_GRID_LIST_URL = "/list";
    public static final String PATIENT_GET_SUBMIT_REVIEW_URL = "/review/new/{doctorUsername}";
    public static final String PATIENT_GET_EDIT_REVIEW_URL = "/review/edit/{doctorUsername}";
    public static final String PATIENT_POST_SUBMIT_REVIEW_URL = "/review/new";
    public static final String PATIENT_PUT_REVIEW_URL = "/review/edit";
    public static final String PATIENT_DELETE_REVIEW_URL = "/review/delete/{doctorUsername}";
    public static final String PATIENT_NEW_APPOINTMENT_POST_URL = "/appointment/new";
    public static final String APPOINTMENT_CONFIRMATION_URL = "/appointment/confirmation";
    /* END OF PATIENT URL */


    /* DOCTOR URLs */
    public static final String DOCTOR_ROOT_URL = "/doctor/";
    public static final String DOCTOR_INDEX_URL_1 = "/doctor";
    public static final String DOCTOR_INDEX_URL_2 = "/doctor/index";
    public static final String DOCTOR_BOOKINGS_URL = "/doctor/appointments";
    public static final String DOCTOR_PROFILE_URL = "/doctor/profile";
    public static final String DOCTOR_REVIEWS_URL = "/doctor/reviews";
    /* END OF DOCTOR URL */


    /* AUTH URLs */
    public static final String LOGIN_URL = "/login";
    public static final String REGISTER_URL = "/register";
    public static final String LOGOUT_URL = "/logout";
    public static final String REGISTER_CONFIRMATION_URL = "/register/confirmation";
    /* END OF AUTH URLs */


    /* ADMIN URLs */
    public static final String ADMIN_ROOT_URL = "/admin/";
    public static final String ADMIN_INDEX_URL_1 = "/admin";
    public static final String ADMIN_INDEX_URL_2 = "/admin/index";
    public static final String ADMIN_VALIDATIONS_URL = "/admin/validations";
    public static final String ADMIN_BANNED_DOCTORS_URL = "/admin/banned-users";
    public static final String ADMIN_VIEW_URL = "/admin/view-doctors";
    public static final String ADMIN_PATIENTS_URL = "/admin/view-patients";
    public static final String ADMIN_PROFILE_URL = "/admin/profile";
    /* END OF ADMIN URL */

    /* -------------------------------------------------------------------------- */


    /* ---------------------------- HTML FILE NAMES ------------------------------------ */
    /* PATIENT HTML FILE NAMES */
    public static final String PATIENT_INDEX_FILE = "patient/index";
    public static final String PATIENT_PROFILE_FILE = "patient/profile";
    public static final String PATIENT_DETAIL_PAGE_FILE = "patient/detail-page";
    public static final String PATIENT_GRID_LIST_FILE = "patient/doctor-list";
    public static final String PATIENT_SUBMIT_REVIEW_FILE = "patient/submit-review";
    public static final String PATIENT_EDIT_REVIEW_FILE = "patient/edit-review";
    public static final String APPOINTMENT_CONFIRMATION_FILE = "patient/appointment-confirmation";
    /* END OF PATIENT HTML FILE NAMES */


    /* DOCTOR HTML FILE NAMES */
    public static final String DOCTOR_INDEX_FILE = "doctor/index";
    public static final String DOCTOR_BOOKINGS_FILE = "doctor/appointments";
    public static final String DOCTOR_PROFILE_FILE = "doctor/doctor-profile";
    public static final String DOCTOR_REVIEWS_FILE = "doctor/reviews";
    /* END OF DOCTOR HTML FILE NAMES */


    /* AUTH HTML FILE NAMES */
    public static final String LOGIN_FILE = "auth/login";
    public static final String REGISTER_FILE = "auth/register";
    public static final String REGISTER_CONFIRMATION_FILE = "auth/register-confirmation";
    /* END OF AUTH HTML FILE NAMES */


    /* ADMIN HTML FILE NAMES */
    public static final String ADMIN_INDEX_FILE = "admin/index";
    public static final String ADMIN_VALIDATIONS_FILE = "admin/validations";
    public static final String ADMIN_BANNED_DOCTORS_FILE = "admin/banned-users";
    public static final String ADMIN_VIEW_FILE = "admin/view-doctors";
    public static final String ADMIN_PATIENTS_FILE = "admin/view-patients";
    public static final String ADMIN_PROFILE_FILE = "admin/admin-profile";
    /* END OF ADMIN HTML FILE NAMES */
}