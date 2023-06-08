package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findByUser_username(String username);

    Doctor findByAfm(String afm);

    Page<Doctor> findAll(Pageable pageable);
}
