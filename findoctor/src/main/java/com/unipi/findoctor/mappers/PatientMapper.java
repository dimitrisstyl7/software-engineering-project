package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.dto.UserDto;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.models.User;
import com.unipi.findoctor.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class PatientMapper {
    private final UserService userService;

    public PatientDto mapToPatientDto(Patient patient) {
        UserDto userDto = UserDto.builder()
                .username(patient.getUser().getUsername())
                .name(patient.getUser().getName())
                .surname(patient.getUser().getSurname())
                .email(patient.getUser().getEmail())
                .userType(patient.getUser().getUserType())
                .phone(patient.getUser().getPhone())
                .build();

        return PatientDto.builder()
                .amka(patient.getAmka())
                .userDto(userDto)
                .dateOfBirth(patient.getDateOfBirth())
                .registeredOn(patient.getRegisteredOn())
                .appointments(patient.getAppointments())
                .ratings(patient.getRatings())
                .build();
    }

    public Patient mapToPatient(PatientDto patientDto) {
        User user = userService.findByUsername(patientDto.getUserDto().getUsername());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        user.setName(patientDto.getUserDto().getName());
        user.setSurname(patientDto.getUserDto().getSurname());
        user.setPhone(patientDto.getUserDto().getPhone());
        user.setEmail(patientDto.getUserDto().getEmail());

        return Patient.builder()
                .amka(patientDto.getAmka())
                .user(user)
                .dateOfBirth(patientDto.getDateOfBirth())
                .registeredOn(patientDto.getRegisteredOn())
                .ratings(patientDto.getRatings())
                .appointments(patientDto.getAppointments())
                .build();
    }
}
