package com.unipi.findoctor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admin_tbl")
public class Admin {
    @Id
    @OneToOne
    @JoinColumn(referencedColumnName = "username")
    private User user;
}
