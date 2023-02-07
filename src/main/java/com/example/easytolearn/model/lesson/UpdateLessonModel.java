package com.example.easytolearn.model.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateLessonModel implements BaseLessonModel {
    private Long id;
    private String lessonInfo;
    private String lessonUrl;
    private Boolean isVisible;

    @Override
    public Long getCourseId() {
        return null;
    }
}
