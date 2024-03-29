package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.LessonConverter;
import com.example.easytolearn.entity.Course;
import com.example.easytolearn.entity.Lesson;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.lesson.BaseLessonModel;
import com.example.easytolearn.model.lesson.CreateLessonModel;
import com.example.easytolearn.model.lesson.LessonModel;
import com.example.easytolearn.model.lesson.UpdateLessonModel;
import com.example.easytolearn.repository.LessonRepository;
import com.example.easytolearn.service.CourseService;
import com.example.easytolearn.service.LessonService;
import com.example.easytolearn.service.UserCourseMappingService;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserCourseMappingService userCourseMappingService;
    private final LessonRepository lessonRepository;
    private final LessonConverter lessonConverter;

    @Override
    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public LessonModel createLesson(CreateLessonModel createLessonModel) {
        validateVariablesForNullOrIsEmpty(createLessonModel);
        validateLengthVariables(createLessonModel);

        Lesson lesson = new Lesson();
        lesson.setLessonInfo(createLessonModel.getLessonInfo());
        lesson.setLessonUrl(createLessonModel.getLessonUrl());
        lesson.setIsVisible(createLessonModel.getIsVisible());

        Course course = new Course();
        course.setId(createLessonModel.getCourseId());
        lesson.setCourse(course);
        save(lesson);
        return lessonConverter.convertFromEntity(lesson);
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public LessonModel getLessonModelById(Long id) {
        return lessonConverter.convertFromEntity(getById(id));
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Long getCountLessonByCourseId(Long courseId) {
        return lessonRepository.getCountLessonByCourseId(courseId);
    }

    @Override
    public List<LessonModel> getAllByCourseId(Long courseId) {
        return lessonRepository
                .findAllByCourse_IdOrderByIdAsc(courseId)
                .stream()
                .map(lessonConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonModel> getFirstThreeLessonsByCourseId(Long courseId) {
        return lessonRepository
                .findFirstThreeLessonsByCourseId(courseId)
                .stream()
                .map(lessonConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public LessonModel updateLesson(UpdateLessonModel updateLessonModel) {
        Long lessonId = updateLessonModel.getId();
        Lesson dataLesson = getDataLessonByIdWithCheckAccess(lessonId);

        validateVariablesForNullOrIsEmptyUpdate(updateLessonModel);
        validateLengthVariables(updateLessonModel);

        setVariablesForUpdateLesson(dataLesson, updateLessonModel);

        lessonRepository.save(dataLesson);
        return lessonConverter.convertFromEntity(dataLesson);
    }

    @Override
    public LessonModel deleteLessonById(Long id) {
        Lesson deleteLesson = getDataLessonByIdWithCheckAccess(id);
        lessonRepository.delete(deleteLesson);
        return lessonConverter.convertFromEntity(deleteLesson);
    }

    private Lesson getDataLessonByIdWithCheckAccess(Long id) {
        if (id == null)
            throw new ApiFailException("Не указан ID урока");

        Lesson dataLesson = getById(id);

        if (dataLesson == null)
            throw new ApiFailException("Урок под ID " + id + " не найден");

        Long currentUserId = userService.getCurrentUser().getId();
        Long authorCourseId = dataLesson.getCourse().getUser().getId();

        if (!currentUserId.equals(authorCourseId))
            throw new ApiFailException("Доступ ограничен");

        return dataLesson;
    }
    
    private void validateLengthVariables(BaseLessonModel baseLessonModel) {
        if (baseLessonModel.getLessonInfo() != null && baseLessonModel.getLessonInfo().length() > 1000)
            throw new ApiFailException("Длинна символов информации урока ограниченно(1000)");
    }

    private void validateVariablesForNullOrIsEmpty(CreateLessonModel createLessonModel) {
        if (createLessonModel.getLessonInfo() == null || createLessonModel.getLessonInfo().isEmpty())
            throw new ApiFailException("Информация об уроке не заполнено");
        if (createLessonModel.getIsVisible() == null)
            throw new ApiFailException("Lesson visible is not specified");
        if (createLessonModel.getCourseId() == null)
            throw new ApiFailException("Не казан ID курса");
        else {
            Long courseId = createLessonModel.getCourseId();
            Course course = courseService.getById(courseId);
            if (course == null)
                throw new ApiFailException("Курс под ID " + courseId + " не найден");
        }
    }

    private void validateVariablesForNullOrIsEmptyUpdate(UpdateLessonModel updateLessonModel) {
        if (updateLessonModel.getLessonInfo() != null && updateLessonModel.getLessonInfo().isEmpty())
            throw new ApiFailException("Информация об уроке не заполнено");
    }

    private void setVariablesForUpdateLesson(Lesson lesson, UpdateLessonModel updateLessonModel) {
        if (updateLessonModel.getLessonUrl() != null)
            lesson.setLessonUrl(updateLessonModel.getLessonUrl());
        if (updateLessonModel.getLessonInfo() != null)
            lesson.setLessonInfo(updateLessonModel.getLessonInfo());
        if (updateLessonModel.getIsVisible() != null)
            lesson.setIsVisible(updateLessonModel.getIsVisible());
    }
}