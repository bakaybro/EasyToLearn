package com.example.easytolearn.converter;

import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserRole;
import com.example.easytolearn.model.UserRoleModel;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter extends BaseConverter<UserRoleModel, UserRole> {

    public UserRoleConverter() {
        super(UserRoleConverter::convertToEntity, UserRoleConverter::convertToModel);
    }

    private static UserRoleModel convertToModel(UserRole entityToConvert) {
        if (entityToConvert == null) return null;

        return UserRoleModel.builder()
                .id(entityToConvert.getId())
                .roleName(entityToConvert.getRoleName())
                .userId(entityToConvert.getUser().getId())
                .build();
    }

    private static UserRole convertToEntity(UserRoleModel modelToConvert) {
        if (modelToConvert == null) return null;

        UserRole userRole = new UserRole();
        userRole.setId(modelToConvert.getId());
        userRole.setRoleName(modelToConvert.getRoleName());

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            userRole.setUser(user);
        }
        return userRole;
    }
}