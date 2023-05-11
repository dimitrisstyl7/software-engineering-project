package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_tbl")
public class User {
    @Id
    private String username;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private String password;
    private String email;
    private String userType;

    @OneToOne(mappedBy = "user")
    private Admin admin;

    @OneToOne(mappedBy = "user")
    private RegisteredUser registeredUser;
}
