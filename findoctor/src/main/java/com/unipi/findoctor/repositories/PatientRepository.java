package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByAmka(String amka);
}
