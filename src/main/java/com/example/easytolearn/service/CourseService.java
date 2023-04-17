package com.example.easytolearn.service;

import com.example.easytolearn.entity.Course;
import com.example.easytolearn.model.course.CourseDataModel;
import com.example.easytolearn.model.course.CourseModel;
import com.example.easytolearn.model.course.CreateCourseModel;
import com.example.easytolearn.model.course.UpdateCourseModel;

import java.util.List;

public interface CourseService extends BaseService<Course> {
    CourseDataModel createCourse(CreateCourseModel createCourseModel);

    CourseDataModel updateCourse(UpdateCourseModel updateCourseModel);

    CourseModel deleteCourseById(Long id);

    CourseDataModel getCourseModelById(Long id);

    CourseDataModel getCourseDataModelByCourseId(Long courseId);

    List<CourseDataModel> getAllCourseDataModel();

    List<CourseDataModel> getAllCourseDataModelByUserId(Long userId);

    List<CourseDataModel> getAllCourseDataModelByCategoryId(Long id);

    List<CourseDataModel> getAllCourseDataModelByCourseName(String courseName);

    List<CourseDataModel> getAllCourseDataModelByCategoryName(String courseName);
}