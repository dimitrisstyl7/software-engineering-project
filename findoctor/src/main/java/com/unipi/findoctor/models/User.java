package com.unipi.findoctor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user_tbl")
public class User {
    @Id
    private String username;

    private String email;
    private String password;
    private String userType;
    private String name;
    private String surname;
    private String phone;
}