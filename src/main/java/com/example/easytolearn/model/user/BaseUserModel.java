package com.example.easytolearn.model.user;

import java.time.LocalDate;

public interface BaseUserModel {
    String getFullName();
    LocalDate getBirthDay();
    String getUsername();
    String getEmail();
    String getPassword();
}
