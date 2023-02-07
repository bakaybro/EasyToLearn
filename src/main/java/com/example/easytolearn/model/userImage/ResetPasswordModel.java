package com.example.easytolearn.model.userImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ResetPasswordModel {
    private String encodeEmail;
    private String password;
}
