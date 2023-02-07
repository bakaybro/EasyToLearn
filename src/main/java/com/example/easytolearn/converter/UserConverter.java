package com.example.easytolearn.converter;

import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.user.BaseUserModel;
import com.example.easytolearn.model.user.UpdateUserModel;
import com.example.easytolearn.model.user.UserModelToSend;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<BaseUserModel, User> {

    public UserConverter() {
        super(UserConverter::convertToEntity, UserConverter::convertToModel);
    }

    private static BaseUserModel convertToModel(User entityToConvert) {
        if (entityToConvert == null) return null;

        return UserModelToSend.builder()
                .id(entityToConvert.getId())
                .birthDay(entityToConvert.getBirthDay())
                .fullName(entityToConvert.getFullName())
                .email(entityToConvert.getEmail())
                .username(entityToConvert.getUsername())
                .isActive(entityToConvert.getIsActive())
                .build();
    }

    private static User convertToEntity(BaseUserModel modelToConvert) {
        if (modelToConvert == null) return null;

        User user = new User();
        if (modelToConvert instanceof UpdateUserModel) {
            user.setId(((UpdateUserModel) modelToConvert).getId());
        }

        user.setFullName(modelToConvert.getFullName());
        user.setUsername(modelToConvert.getUsername());
        user.setEmail(modelToConvert.getEmail());
        user.setPassword(modelToConvert.getPassword());

        return user;
    }
}
