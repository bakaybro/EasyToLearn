package com.example.easytolearn.model.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateLessonModel implements BaseLessonModel {
    private String lessonIndo;
    private String lessonUrl;
    private Boolean isVisible;
    private Long courseId;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getLessonInfo() {
        return null;
    }
}
