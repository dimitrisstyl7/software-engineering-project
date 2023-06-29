package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findByUser_username(String username);

    Doctor findByAfm(String afm);

    @Query("SELECT d FROM Doctor d WHERE d.status = 'approved'")
    Page<Doctor> findAll(Pageable pageable);

    @Query("SELECT d FROM Doctor d WHERE (LOWER(d.user.name) LIKE CONCAT('%', LOWER(:query), '%') OR LOWER(d.user.surname) LIKE CONCAT('%', LOWER(:query), '%') OR LOWER(d.specialization) LIKE CONCAT('%', LOWER(:query), '%')) AND d.status = 'approved' ")
    Page<Doctor> searchDoctors(String query, Pageable pageable);
}
