package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.UserDto;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.User;
import com.unipi.findoctor.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class DoctorMapper {
    private final UserService userService;

    public DoctorDto mapToDoctorDto(Doctor doctor) {
        UserDto userDto = UserDto.builder()
                .username(doctor.getUser().getUsername())
                .name(doctor.getUser().getName())
                .surname(doctor.getUser().getSurname())
                .email(doctor.getUser().getEmail())
                .userType(doctor.getUser().getUserType())
                .phone(doctor.getUser().getPhone())
                .build();

        return DoctorDto.builder()
                .afm(doctor.getAfm())
                .user(userDto)
                .specialization(doctor.getSpecialization())
                .registeredOn(doctor.getRegisteredOn())
                .dateOfBirth(doctor.getDateOfBirth())
                .businessPhone(doctor.getBusinessPhone())
                .city(doctor.getCity())
                .imageURL(doctor.getImageURL())
                .status(doctor.getStatus())
                .address(doctor.getAddress())
                .ratings(doctor.getRatings())
                .views(doctor.getViews())
                .build();
    }
    public Doctor mapToDoctor(DoctorDto doctor) {
        return Doctor.builder()
                .afm(doctor.getAfm())
                .user(doctor.getUser())
                .dateOfBirth(doctor.getDateOfBirth())
                .registeredOn(doctor.getRegisteredOn())
                .specialization(doctor.getSpecialization())
                .businessPhone(doctor.getBusinessPhone())
                .city(doctor.getCity())
                .imageName(doctor.getImageName())
                .status(doctor.getStatus())
                .address(doctor.getAddress())
                .dateOfBirth(doctor.getDateOfBirth())
                .ratings(doctor.getRatings())
                .build();
    }

    public Doctor mapToDoctor(DoctorDto doctorDto) {
        User user = userService.findByUsername(doctorDto.getUser().getUsername());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        user.setName(doctorDto.getUser().getName());
        user.setSurname(doctorDto.getUser().getSurname());
        user.setPhone(doctorDto.getUser().getPhone());
        user.setEmail(doctorDto.getUser().getEmail());

        return Doctor.builder()
                .afm(doctorDto.getAfm())
                .user(user)
                .specialization(doctorDto.getSpecialization())
                .businessPhone(doctorDto.getBusinessPhone())
                .city(doctorDto.getCity())
                .imageName(doctorDto.getImageName())
                .status(doctorDto.getStatus())
                .address(doctorDto.getAddress())
                .dateOfBirth(doctorDto.getDateOfBirth())
                .ratings(doctorDto.getRatings())
                .build();
    }
}
