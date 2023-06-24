package com.unipi.findoctor.security;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.dto.UserDto;
import com.unipi.findoctor.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUtil {
    private PatientService patientService;

    public UserDto getSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return new UserDto(
                    authentication.getName(),
                    authentication.getAuthorities().toArray()[0].toString()
            );
        }
        return null;
    }

    public PatientDto getSessionPatient() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        String userType = authentication.getAuthorities().toArray()[0].toString();
        if (!userType.equals("patient")) {
            return null;
        }

        return patientService.findPatient(authentication.getName());
    }

    public boolean isPatientLoggedIn() {
        PatientDto patientDto = getSessionPatient();

        if (patientDto == null) {
            return false;
        }
        return true;

    }

}
