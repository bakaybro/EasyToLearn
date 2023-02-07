package com.example.easytolearn.converter;

import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserAuth;
import com.example.easytolearn.model.UserAuthModel;
import org.springframework.stereotype.Component;

@Component
public class UserAuthConverter extends BaseConverter<UserAuthModel, UserAuth> {

    public UserAuthConverter() {
        super(UserAuthConverter::convertToEntity, UserAuthConverter::convertToModel);
    }

    private static UserAuthModel convertToModel(UserAuth entityToConvert) {
        if (entityToConvert == null) return null;

        return UserAuthModel.builder()
                .id(entityToConvert.getId())
                .userId(entityToConvert.getUser().getId())
                .createDate(entityToConvert.getCreatedDate())
                .isSuccess(entityToConvert.getIsSuccess())
                .build();
    }

    private static UserAuth convertToEntity(UserAuthModel modelToConvert) {
        if (modelToConvert == null) return null;

        UserAuth userAuth = new UserAuth();
        userAuth.setId(modelToConvert.getId());
        userAuth.setCreatedDate(modelToConvert.getCreateDate());
        userAuth.setIsSuccess(modelToConvert.getIsSuccess());

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getId());
            userAuth.setUser(user);
        }
        return userAuth;
    }
}
