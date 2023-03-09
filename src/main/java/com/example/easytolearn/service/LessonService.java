package com.example.easytolearn.service;

import com.example.easytolearn.entity.Lesson;
import com.example.easytolearn.model.lesson.CreateLessonModel;
import com.example.easytolearn.model.lesson.LessonModel;
import com.example.easytolearn.model.lesson.UpdateLessonModel;

import java.util.List;

public interface LessonService extends BaseService<Lesson> {
    LessonModel createLesson(CreateLessonModel createLessonModel);
    LessonModel updateLesson(UpdateLessonModel updateLessonModel);
    LessonModel deleteLessonById(Long id);
    LessonModel getLessonModelById(Long id);
    List<LessonModel> getAllByCourseId(Long courseId);
    List<LessonModel> getFirstThreeLessonsByCourseId(Long courseId);
    Long getCountLessonByCourseId(Long courseId);
}
