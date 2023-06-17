package com.unipi.findoctor.mappers;

import com.unipi.findoctor.dto.AppointmentDto;
import com.unipi.findoctor.models.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public AppointmentDto mapToAppointmentDto(Appointment appointment){
        return AppointmentDto.builder()
                .id(appointment.getId())
                .patient(appointment.getPatient())
                .doctor(appointment.getDoctor())
                .date(appointment.getDate())
                .time_slot(appointment.getTime_slot())
                .build();
    }

    public Appointment mapToAppointment(AppointmentDto appointmentDto){
        return Appointment.builder()
                .id(appointmentDto.getId())
                .patient(appointmentDto.getPatient())
                .doctor(appointmentDto.getDoctor())
                .date(appointmentDto.getDate())
                .time_slot(appointmentDto.getTime_slot())
                .build();
    }
}
