package com.example.easytolearn.converter;

import com.example.easytolearn.entity.Category;
import com.example.easytolearn.entity.Course;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.course.CourseModel;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter extends BaseConverter<CourseModel, Course> {

    public CourseConverter() {
        super(CourseConverter::convertToEntity, CourseConverter::convertToModel);
    }

    private static CourseModel convertToModel(Course entityToConvert) {
        if (entityToConvert == null) return null;

        Long categoryId = null;
        if (entityToConvert.getCategory() != null)
            categoryId = entityToConvert.getCategory().getId();

        return CourseModel.builder()
                .id(entityToConvert.getId())
                .categoryId(categoryId)
                .courseName(entityToConvert.getCourseName())
                .email(entityToConvert.getEmail())
                .phoneNumber(entityToConvert.getPhoneNumber())
                .courseShortInfo(entityToConvert.getCourseShortInfo())
                .courseInfoTitle(entityToConvert.getCourseInfoTitle())
                .courseInfo(entityToConvert.getCourseInfo())
                .courseInfoUrl(entityToConvert.getCourseInfoUrl())
                .price(entityToConvert.getPrice())
                .userId(entityToConvert.getUser().getId())
                .build();
    }

    private static Course convertToEntity(CourseModel modelToConvert) {
        if (modelToConvert == null) return null;

        Course course = new Course();
        course.setId(modelToConvert.getId());
        course.setCourseName(modelToConvert.getCourseName());
        course.setEmail(modelToConvert.getEmail());
        course.setPhoneNumber(modelToConvert.getPhoneNumber());
        course.setCourseShortInfo(modelToConvert.getCourseShortInfo());
        course.setCourseInfoTitle(modelToConvert.getCourseInfoTitle());
        course.setCourseInfo(modelToConvert.getCourseInfo());
        course.setCourseInfoUrl(modelToConvert.getCourseInfoUrl());
        course.setPrice(modelToConvert.getPrice());

        if (modelToConvert.getCategoryId() != null) {
            Category category = new Category();
            category.setId(modelToConvert.getCategoryId());
            course.setCategory(category);
        }

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            course.setUser(user);
        }
        return course;
    }
}