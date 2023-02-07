package com.example.easytolearn.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CourseModel implements BaseCourseModel {
    private Long id;
    private Long CategoryId;
    private String courseName;
    private String email;
    private String phoneNumber;
    private String courseShortInfo;
    private String courseInfoTitle;
    private String courseInfo;
    private String courseInfoUrl;
    private BigDecimal price;
    private Long userId;
}
