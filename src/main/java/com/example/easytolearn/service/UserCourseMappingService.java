package com.example.easytolearn.service;

import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserCourseMapping;
import com.example.easytolearn.model.course.CourseDataModel;
import com.example.easytolearn.model.UserCourseMappingModel;
import com.example.easytolearn.model.user.BaseUserModel;

import java.util.List;

public interface UserCourseMappingService extends BaseService<UserCourseMapping> {
    UserCourseMappingModel createByCourseId(Long courseId);

    UserCourseMappingModel getUserCourseMappingModelById(Long id);

    List<BaseUserModel> getAllCustomersByCourseId(Long courseId);

    List<CourseDataModel> getAllPurchasedCourses(Long userId);

    UserCourseMapping getByCourseIdAndUserId(Long courseId, Long userId);
}