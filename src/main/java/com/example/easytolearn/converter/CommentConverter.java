package com.example.easytolearn.converter;

import com.example.easytolearn.entity.Comment;
import com.example.easytolearn.entity.Course;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.comment.CommentModel;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter extends BaseConverter<CommentModel, Comment> {

    public CommentConverter() {
        super(CommentConverter::convertToEntity, CommentConverter::convertToModel);
    }

    private static CommentModel convertToModel(Comment entityToConvert) {
        if (entityToConvert == null) return null;

//        CommentModel commentModel = new CommentModel();
//        commentModel.setId(entityToConvert.getId());
//        commentModel.setComment(entityToConvert.getCourseComment());
//        commentModel.setUserId(entityToConvert.getUser().getId());
//        commentModel.setUsername(entityToConvert.getUser().getUsername());
//        commentModel.setCourseId(entityToConvert.getCourse().getId());
//        commentModel.setUserImageUrl(entityToConvert.getUserImage());

        return CommentModel.builder()
                .id(entityToConvert.getId())
                .comment(entityToConvert.getCourseComment())
                .userId(entityToConvert.getUser().getId())
                .username(entityToConvert.getUser().getUsername())
                .userImageUrl(entityToConvert.getUserImageUrl())
                .courseId(entityToConvert.getCourse().getId())
                .build();
    }

    private static Comment convertToEntity(CommentModel modelToConvert) {
        if (modelToConvert == null) return null;

        Comment comment = new Comment();
        comment.setId(modelToConvert.getId());
        comment.setCourseComment(modelToConvert.getComment());
        comment.setUserImageUrl(modelToConvert.getUserImageUrl());

        if (modelToConvert.getCourseId() != null) {
            Course course = new Course();
            course.setId(modelToConvert.getCourseId());
            comment.setCourse(course);
        }

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            comment.setUser(user);
        }
        return comment;
    }
}