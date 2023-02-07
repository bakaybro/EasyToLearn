package com.example.easytolearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserCourseMappingModel {
    private Long id;
    private Long userId;
    private Long courseId;
}
