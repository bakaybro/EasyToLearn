package com.example.easytolearn.converter;

import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserImage;
import com.example.easytolearn.model.userImage.UserImageModel;
import org.springframework.stereotype.Component;

@Component
public class UserImageConverter extends BaseConverter<UserImageModel, UserImage> {

    public UserImageConverter() {
        super(UserImageConverter::convertToEntity, UserImageConverter::convertToModel);

    }

    private static UserImageModel convertToModel(UserImage entityToConvert) {
        if (entityToConvert == null) return null;
        return UserImageModel.builder()
                .id(entityToConvert.getId())
                .userImageUrl(entityToConvert.getUserImageUrl())
                .userId(entityToConvert.getUser().getId())
                .build();
    }

    private static UserImage convertToEntity(UserImageModel modelToConvert) {
        if (modelToConvert == null) return null;

        UserImage userImage = new UserImage();
        userImage.setId(modelToConvert.getId());
        userImage.setUserImageUrl(modelToConvert.getUserImageUrl());

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            userImage.setUser(user);
        }
        return userImage;
    }
}
