package com.example.easytolearn.controller;

import com.example.easytolearn.model.courseProgram.CourseProgramModel;
import com.example.easytolearn.model.courseProgram.CreateCourseProgramModel;
import com.example.easytolearn.model.courseProgram.UpdateCourseProgramModel;
import com.example.easytolearn.util.ResponseMessage;
import com.example.easytolearn.service.CourseProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-program")
@RequiredArgsConstructor
public class CourseProgramController {
    private final CourseProgramService courseProgramService;

    @PostMapping("/create")
    public ResponseMessage<CourseProgramModel> save(@RequestBody CreateCourseProgramModel createCourseProgramModel) {
        return new ResponseMessage<CourseProgramModel>()
                .prepareSuccessMessage(courseProgramService.createCourseProgram(createCourseProgramModel));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<CourseProgramModel> getById(@PathVariable Long id) {
        return new ResponseMessage<CourseProgramModel>()
                .prepareSuccessMessage(courseProgramService.getCourseProgramModelById(id));
    }

    @GetMapping("/get-all/by-course-id/{courseId}")
    public ResponseMessage<List<CourseProgramModel>> getAllByCourseId(@PathVariable Long courseId) {
        return new ResponseMessage<List<CourseProgramModel>>()
                .prepareSuccessMessage(courseProgramService.getAllCourseProgramModelByCourseId(courseId));
    }

    @PutMapping("/update")
    public ResponseMessage<CourseProgramModel> update(@RequestBody UpdateCourseProgramModel updateCourseProgramModel) {
        return new ResponseMessage<CourseProgramModel>()
                .prepareSuccessMessage(courseProgramService.updateCurseProgram(updateCourseProgramModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<CourseProgramModel> delete(@PathVariable Long id) {
        return new ResponseMessage<CourseProgramModel>()
                .prepareSuccessMessage(courseProgramService.deleteCourseProgram(id));
    }
}