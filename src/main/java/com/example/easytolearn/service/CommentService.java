package com.example.easytolearn.service;

import com.example.easytolearn.entity.Comment;
import com.example.easytolearn.model.comment.CommentModel;
import com.example.easytolearn.model.comment.CreateCommentModel;
import com.example.easytolearn.model.comment.UpdateCommentModel;

import java.util.List;

public interface CommentService extends BaseService<Comment> {
    CommentModel createComment(CreateCommentModel createCommentModel);
    CommentModel updateModel(UpdateCommentModel updateCommentModel);
    CommentModel deleteCommentById(Long id);
    CommentModel getCommentModelById(Long id);
    List<CommentModel> getAllCommentModelByCourseId(Long id);
}
