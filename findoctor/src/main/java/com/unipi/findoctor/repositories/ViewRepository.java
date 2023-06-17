package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    int countByDoctor_User_username(String username);
}
