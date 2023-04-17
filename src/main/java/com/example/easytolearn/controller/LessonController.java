package com.example.easytolearn.controller;

import com.example.easytolearn.model.lesson.CreateLessonModel;
import com.example.easytolearn.model.lesson.LessonModel;
import com.example.easytolearn.model.lesson.UpdateLessonModel;
import com.example.easytolearn.util.ResponseMessage;
import com.example.easytolearn.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping("/create")
    public ResponseMessage<LessonModel> save(@RequestBody CreateLessonModel createLessonModel) {
        return new ResponseMessage<LessonModel>()
                .prepareSuccessMessage(lessonService.createLesson(createLessonModel));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<LessonModel> getById(@PathVariable Long id) {
        return new ResponseMessage<LessonModel>()
                .prepareSuccessMessage(lessonService.getLessonModelById(id));
    }

    @GetMapping("/get-all/by-course-id/{courseId}")
    public ResponseMessage<List<LessonModel>> getAllByCourseId(@PathVariable Long courseId) {
        return new ResponseMessage<List<LessonModel>>()
                .prepareSuccessMessage(lessonService.getAllByCourseId(courseId));
    }

    @GetMapping("/get-count-lesson/by-course_id/{courseId}")
    public ResponseMessage<Long> getCountLessonByCourseId(@PathVariable Long courseId) {
        return new ResponseMessage<Long>()
                .prepareSuccessMessage(lessonService.getCountLessonByCourseId(courseId));
    }

    @PutMapping("/update")
    public ResponseMessage<LessonModel> update(@RequestBody UpdateLessonModel updateLessonModel) {
        return new ResponseMessage<LessonModel>()
                .prepareSuccessMessage(lessonService.updateLesson(updateLessonModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<LessonModel> delete(@PathVariable Long id) {
        return new ResponseMessage<LessonModel>()
                .prepareSuccessMessage(lessonService.deleteLessonById(id));
    }
}