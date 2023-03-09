package com.example.easytolearn.service;

import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.user.*;
import com.example.easytolearn.model.userImage.ResetPasswordModel;

import java.util.List;

public interface UserService extends BaseService<User> {
    UserProfileDataModel createUser(CreateUserModel createUserModel);
    UserProfileDataModel updateUser(UpdateUserModel updateUserModel);
    User setInActiveUser(User user, Long status);
    BaseUserModel deleteUser();
    BaseUserModel deleteUserByAdmin();
    User getCurrentUser();
    User getByUsername(String name);
    User getByEmail(String email);
    BaseUserModel getUserModelById(Long id);
    BaseUserModel getCurrentUserModel();
    List<BaseUserModel> getAllUserModels();
    BaseUserModel resetPassword(ResetPasswordModel resetPasswordModel);
    UserProfileDataModel getBasicAuthorizeHeaderByAuthorizeModel(UserAuthorizationModel userAuthorizationModel);
}
