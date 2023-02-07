package com.example.easytolearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CourseImageModel {
    private Long id;
    private String courseImageUrl;
    private Long courseId;
}
