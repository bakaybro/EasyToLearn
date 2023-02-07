package com.example.easytolearn.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseUserModel {
    private String getFullName;
    private LocalDateTime getBirthDay;
    private String getUsername;
    private String getEmail;
    private String getPassword;
}
