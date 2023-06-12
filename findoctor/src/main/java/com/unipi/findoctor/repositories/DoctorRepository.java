package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
