package com.unipi.findoctor.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Override
    public String toString() {
        return "User(" +
                "username='" + getUsername() + '\''
                + ')';
    }

}