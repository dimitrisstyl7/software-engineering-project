package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAppointmentsByDoctor_User_UsernameAndDate(String username, LocalDate date);

    Appointment save(Appointment appointment);
}
