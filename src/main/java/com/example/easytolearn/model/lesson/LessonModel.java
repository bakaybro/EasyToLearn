package com.example.easytolearn.model.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LessonModel {
    private Long id;
    private String lessonInfo;
    private String lessonUrl;
    private Boolean isVisible;
    private Long courseId;
}