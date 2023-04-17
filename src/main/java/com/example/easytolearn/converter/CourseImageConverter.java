package com.example.easytolearn.converter;

import com.example.easytolearn.entity.Course;
import com.example.easytolearn.entity.CourseImage;
import com.example.easytolearn.model.CourseImageModel;
import org.springframework.stereotype.Component;

@Component
public class CourseImageConverter extends BaseConverter<CourseImageModel, CourseImage> {

    public CourseImageConverter() {
        super(CourseImageConverter::convertToEntity, CourseImageConverter::convertToModel);

    }

    private static CourseImageModel convertToModel(CourseImage entityToConvert) {
        if (entityToConvert == null) return null;
        return CourseImageModel.builder()
                .id(entityToConvert.getId())
                .courseImageUrl(entityToConvert.getCourseImageUrl())
                .courseId(entityToConvert.getCourse().getId())
                .build();
    }

    private static CourseImage convertToEntity(CourseImageModel modelToConvert) {
        if (modelToConvert == null) return null;

        CourseImage courseImage = new CourseImage();
        courseImage.setId(modelToConvert.getId());
        courseImage.setCourseImageUrl(modelToConvert.getCourseImageUrl());

        if (modelToConvert.getCourseId() != null) {
            Course course = new Course();
            course.setId(modelToConvert.getCourseId());
            courseImage.setCourse(course);
        }
        return courseImage;
    }
}