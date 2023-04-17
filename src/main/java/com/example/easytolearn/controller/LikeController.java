package com.example.easytolearn.controller;

import com.example.easytolearn.model.LikeModel;
import com.example.easytolearn.util.ResponseMessage;
import com.example.easytolearn.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/create/{courseId}")
    public ResponseMessage<LikeModel> save(@PathVariable Long courseId) {
        return new ResponseMessage<LikeModel>()
                .prepareSuccessMessage(likeService.createLikeByCourseId(courseId));
    }

    @GetMapping("/get-all/by-course-id/{courseId}")
    public ResponseMessage<List<LikeModel>> getAllByCourseId(@PathVariable Long courseId) {
        return new ResponseMessage<List<LikeModel>>()
                .prepareSuccessMessage(likeService.getAllLikeModelByCourseId(courseId));
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseMessage<LikeModel> delete(@PathVariable Long courseId) {
        return new ResponseMessage<LikeModel>()
                .prepareSuccessMessage(likeService.deleteLike(courseId));
    }
}