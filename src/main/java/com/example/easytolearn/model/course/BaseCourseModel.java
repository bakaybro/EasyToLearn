package com.example.easytolearn.model.course;

import java.math.BigDecimal;

public interface BaseCourseModel {
    Long getId();
    Long getCategoryId();
    String getCourseName();
    String getEmail();
    String getPhoneNumber();
    String getCourseShortInfo();
    String getCourseInfoTitle();
    String getCourseInfo();
    String getCourseInfoUrl();
    BigDecimal getPrice();
    Long getUserId();
}
