package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByAmka(String amka);

    Optional<Patient> findByUser_username(String username);
}
