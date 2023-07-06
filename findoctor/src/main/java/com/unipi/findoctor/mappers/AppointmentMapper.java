package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.AppointmentDetailsDto;
import com.unipi.findoctor.dto.AppointmentDto;
import com.unipi.findoctor.dto.DoctorDto;
import com.unipi.findoctor.dto.PatientDto;
import com.unipi.findoctor.models.Appointment;
import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AppointmentMapper {
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;

    public AppointmentDto mapToAppointmentDto(Appointment appointment) {
        PatientDto patientDto = patientMapper.mapToPatientDto(appointment.getPatient());
        DoctorDto doctorDto = doctorMapper.mapToDoctorDto(appointment.getDoctor());

        return AppointmentDto.builder()
                .id(appointment.getId())
                .patientDto(patientDto)
                .doctorDto(doctorDto)
                .date(appointment.getDate())
                .timeSlot(appointment.getTimeSlot())
                .build();
    }

    public Appointment mapToAppointment(AppointmentDto appointmentDto) {
        Patient patient = patientMapper.mapToPatient(appointmentDto.getPatientDto());
        Doctor doctor = doctorMapper.mapToDoctor(appointmentDto.getDoctorDto());

        return Appointment.builder()
                .id(appointmentDto.getId())
                .patient(patient)
                .doctor(doctor)
                .date(appointmentDto.getDate())
                .timeSlot(appointmentDto.getTimeSlot())
                .build();
    }

    public AppointmentDetailsDto mapToAppointmentDetailsDto(Appointment appointment) {
        return AppointmentDetailsDto.builder()
                .id(appointment.getId())
                .amka(appointment.getPatient().getAmka())
                .name(appointment.getPatient().getUser().getName())
                .surname(appointment.getPatient().getUser().getSurname())
                .phone(appointment.getPatient().getUser().getPhone())
                .timeSlot(appointment.getTimeSlot())
                .build();
    }

    public List<AppointmentDto> mapToAppointmentDtoList(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::mapToAppointmentDto)
                .toList();
    }

    public List<Appointment> mapToAppointmentList(List<AppointmentDto> appointmentDtoList) {
        return appointmentDtoList.stream()
                .map(this::mapToAppointment)
                .toList();
    }
}
