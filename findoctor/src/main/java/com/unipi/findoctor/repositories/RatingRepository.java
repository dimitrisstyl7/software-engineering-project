package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.Doctor;
import com.unipi.findoctor.models.Patient;
import com.unipi.findoctor.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating save(Rating rating);

    Rating findFirstByPatientAndDoctor(Patient patient, Doctor doctor);

    void deleteById(Long id);
}
