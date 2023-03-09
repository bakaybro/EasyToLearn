package com.example.easytolearn.service;

import com.example.easytolearn.entity.CourseProgram;
import com.example.easytolearn.model.courseProgram.CourseProgramModel;
import com.example.easytolearn.model.courseProgram.CreateCourseProgramModel;
import com.example.easytolearn.model.courseProgram.UpdateCourseProgramModel;

import java.util.List;

public interface CourseProgramService extends BaseService<CourseProgram> {
    CourseProgramModel createCourseProgram(CreateCourseProgramModel createCourseProgramModel);
    CourseProgramModel updateCourseProgram(UpdateCourseProgramModel updateCourseProgramModel);
    CourseProgramModel deleteCourseProgram(Long id);
    CourseProgramModel getCourseProgramModelById(Long id);
    List<CourseProgramModel> getAllCourseProgramModelByCourseId(Long courseId);
}
