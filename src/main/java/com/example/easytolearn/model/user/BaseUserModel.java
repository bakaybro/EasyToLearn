package com.example.easytolearn.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

public interface BaseUserModel {
    String getFullName();
    LocalDate getBirthDay();
    String getUsername();
    String getEmail();
    String getPassword();
}
