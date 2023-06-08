package com.unipi.findoctor.repositories;

import com.unipi.findoctor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    User findFirstByUsername(String username);
}

