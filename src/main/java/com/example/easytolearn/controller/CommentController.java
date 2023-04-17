package com.example.easytolearn.controller;

import com.example.easytolearn.model.comment.CommentModel;
import com.example.easytolearn.model.comment.CreateCommentModel;
import com.example.easytolearn.util.ResponseMessage;
import com.example.easytolearn.model.comment.UpdateCommentModel;
import com.example.easytolearn.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseMessage<CommentModel> save(@RequestBody CreateCommentModel createCommentModel) {
        return new ResponseMessage<CommentModel>()
                .prepareSuccessMessage(commentService.createComment(createCommentModel));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseMessage<CommentModel> getById(@PathVariable Long id) {
        return new ResponseMessage<CommentModel>()
                .prepareSuccessMessage(commentService.getCommentModelById(id));
    }

    @GetMapping("/get-all/by-course-id/{courseId}")
    public ResponseMessage<List<CommentModel>> getAllByCourseId(@PathVariable Long courseId) {
        return new ResponseMessage<List<CommentModel>>()
                .prepareSuccessMessage(commentService.getAllCommentModelByCourseId(courseId));
    }

    @PutMapping("/update")
    public ResponseMessage<CommentModel> update(@RequestBody UpdateCommentModel updateCommentModel) {
        return new ResponseMessage<CommentModel>()
                .prepareSuccessMessage(commentService.updateComment(updateCommentModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<CommentModel> delete(@PathVariable Long id) {
        return new ResponseMessage<CommentModel>()
                .prepareSuccessMessage(commentService.deleteComment(id));
    }
}