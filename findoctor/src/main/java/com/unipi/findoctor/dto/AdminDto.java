package com.unipi.findoctor.dto;


import com.unipi.findoctor.models.User;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDto {
    private User user;
}
