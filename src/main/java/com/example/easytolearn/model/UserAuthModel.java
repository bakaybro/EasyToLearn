package com.example.easytolearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthModel {
    private Long id;
    private Long userId;
    private Boolean isSuccess;
    private LocalDateTime createDate;
}
