package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user_tbl")
public class  User {
    @Id
    private String username;

    private String email;
    private String password;
    private String userType;
    private String name;
    private String surname;
    private String phone;

    @OneToOne(mappedBy = "user")
    private Admin admin;

    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    @OneToOne(mappedBy = "user")
    private Patient patient;
}