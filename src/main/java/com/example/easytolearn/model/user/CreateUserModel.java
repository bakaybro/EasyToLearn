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
public class CreateUserModel {
    private String fullName;
    private LocalDateTime birthDay;
    private String username;
    private String email;
    private String password;
}
