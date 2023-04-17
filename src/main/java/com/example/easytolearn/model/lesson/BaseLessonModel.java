package com.example.easytolearn.model.lesson;

public interface BaseLessonModel {
     Long getId();
     String getLessonInfo();
     String getLessonUrl();
     Boolean getIsVisible();
     Long getCourseId();
}