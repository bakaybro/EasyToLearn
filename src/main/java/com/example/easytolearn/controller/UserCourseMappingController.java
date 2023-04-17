package com.example.easytolearn.controller;

import com.example.easytolearn.model.course.CourseDataModel;
import com.example.easytolearn.model.user.BaseUserModel;
import com.example.easytolearn.service.BaseService;
import com.example.easytolearn.util.ResponseMessage;
import com.example.easytolearn.model.UserCourseMappingModel;
import com.example.easytolearn.service.UserCourseMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class UserCourseMappingController {
    private final UserCourseMappingService userCourseMappingService;

    @PostMapping("/create-by-course-id/{courseId}")
    public ResponseMessage<UserCourseMappingModel> create(@PathVariable Long courseId) {
        return new ResponseMessage<UserCourseMappingModel>()
                .prepareSuccessMessage(userCourseMappingService.createByCourseId(courseId));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<UserCourseMappingModel> getById(@PathVariable Long id) {
        return new ResponseMessage<UserCourseMappingModel>()
                .prepareSuccessMessage(userCourseMappingService.getUserCourseMappingModelById(id));
    }

    @GetMapping("/get-all-customers/by-course-id/{courseId}")
    public ResponseMessage<List<BaseUserModel>> getCustomersByCourseId(@PathVariable Long courseId) {
        return new ResponseMessage<List<BaseUserModel>>()
                .prepareSuccessMessage(userCourseMappingService.getAllCustomersByCourseId(courseId));
    }

    @GetMapping("/get-all-purchased-curses/{userId}")
    public ResponseMessage<List<CourseDataModel>> getAllPurchasedCourses(@PathVariable Long userId) {
        return new ResponseMessage<List<CourseDataModel>>()
                .prepareSuccessMessage(userCourseMappingService.getAllPurchasedCourses(userId));
    }
}