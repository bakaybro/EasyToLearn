package com.example.easytolearn.converter;

import com.example.easytolearn.entity.Course;
import com.example.easytolearn.entity.Like;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.LikeModel;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter extends BaseConverter<LikeModel, Like> {

    public LikeConverter() {
        super(LikeConverter::convertToEntity, LikeConverter::convertToModel);
    }

    private static LikeModel convertToModel(Like entityToConvert) {
        if (entityToConvert == null) return null;

        return LikeModel.builder()
                .id(entityToConvert.getId())
                .userId(entityToConvert.getUser().getId())
                .courseId(entityToConvert.getCourse().getId())
                .build();
    }

    private static Like convertToEntity(LikeModel modelToConvert) {
        if (modelToConvert == null) return null;

        Like like = new Like();
        like.setId(modelToConvert.getId());

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            like.setUser(user);
        }

        if (modelToConvert.getCourseId() != null) {
            Course course = new Course();
            course.setId(modelToConvert.getCourseId());
            like.setCourse(course);
        }
        return like;
    }
}