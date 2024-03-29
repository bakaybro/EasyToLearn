package com.example.easytolearn.controller;

import com.example.easytolearn.model.CourseImageModel;
import com.example.easytolearn.util.ResponseMessage;
import com.example.easytolearn.service.CourseImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/course-image")
@RequiredArgsConstructor
public class CourseImageController {
    private final CourseImageService courseImageService;

    @PostMapping("/create/{courseId}")
    public ResponseMessage<CourseImageModel> save(@RequestParam(name = "file") MultipartFile file,
                                                  @PathVariable Long courseId) {
        return new ResponseMessage<CourseImageModel>()
                .prepareSuccessMessage(courseImageService.createCourseImage(file, courseId));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<CourseImageModel> getById(@PathVariable Long id) {
        return new ResponseMessage<CourseImageModel>()
                .prepareSuccessMessage(courseImageService.getCourseImageModelById(id));
    }

    @GetMapping("/get-by-course-id/{courseId}")
    public ResponseMessage<CourseImageModel> getByCourseId(@PathVariable Long courseId) {
        return new ResponseMessage<CourseImageModel>()
                .prepareSuccessMessage(courseImageService.getCourseImageModelByCourseId(courseId));
    }

    @PutMapping("/update/{id}")
    public ResponseMessage<CourseImageModel> update(@RequestParam(name = "file") MultipartFile multipartFile,
                                                    @PathVariable Long id) {
        return new ResponseMessage<CourseImageModel>()
                .prepareSuccessMessage(courseImageService.updateImage(multipartFile, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<CourseImageModel> delete(@PathVariable Long id) {
        return new ResponseMessage<CourseImageModel>()
                .prepareSuccessMessage(courseImageService.deleteImage(id));
    }
}