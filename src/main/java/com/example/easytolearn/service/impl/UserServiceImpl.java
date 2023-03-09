package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.UserConverter;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserAuth;
import com.example.easytolearn.entity.UserBalance;
import com.example.easytolearn.entity.UserRole;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.user.*;
import com.example.easytolearn.model.userImage.ResetPasswordModel;
import com.example.easytolearn.repository.UserRepository;
import com.example.easytolearn.service.UserAuthService;
import com.example.easytolearn.service.UserBalanceService;
import com.example.easytolearn.service.UserRoleService;
import com.example.easytolearn.service.UserService;
import com.example.easytolearn.util.RegexUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private UserBalanceService userBalanceService;
//    @Autowired
//    private UserCourseMappingService userCourseMappingService;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;
//    private final UserLogService userLogService;
    private final UserConverter userConverter;


    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsActive(1L);
        userRepository.save(user);

        userRoleService.save(UserRole.builder()
                .roleName("ROLE_USER")
                .user(user)
                .build());

        userBalanceService.save(UserBalance.builder()
                .user(user)
                .balance(new BigDecimal(0))
                .build());

        return user;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserProfileDataModel createUser(CreateUserModel createUserModel) {
        validateVariablesForNullOrIsEmpty(createUserModel);
        RegexUtil.validateUsername(createUserModel.getUsername());
        RegexUtil.validateEmail(createUserModel.getEmail());
//        checkUsernameAndEmail(createUserModel);

        String token = getBasicToken(createUserModel.getUsername(), createUserModel.getPassword());

//        User userData = userConverter.convertFromModel(createUserModel);
        return null;
    }

    @Override
    public UserProfileDataModel updateUser(UpdateUserModel updateUserModel) {
        return null;
    }

    @Override
    public User setInActiveUser(User user, Long status) {
        user.setIsActive(status);
        return userRepository.save(user);
    }

    @Override
    public BaseUserModel deleteUser() {
        return null;
    }

    @Override
    public BaseUserModel deleteUserByAdmin() {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public User getByUsername(String name) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public BaseUserModel getUserModelById(Long id) {
        return null;
    }

    @Override
    public BaseUserModel getCurrentUserModel() {
        return null;
    }

    @Override
    public List<BaseUserModel> getAllUserModels() {
        return null;
    }

    @Override
    public BaseUserModel resetPassword(ResetPasswordModel resetPasswordModel) {
        return null;
    }

    @Override
    public UserProfileDataModel getBasicAuthorizeHeaderByAuthorizeModel(UserAuthorizationModel userAuthorizationModel) {
        return null;
    }

    private User getUserDataWithAccessVerification(Long userId) {
        if (userId == null) throw new ApiFailException("Забыли указать ID пользователя");
        User userData = getById(userId);

        if (userData == null) throw new ApiFailException("Не удалось найти пользователя под ID:" + userId);

        if (!userId.equals(getCurrentUser().getId())) throw new ApiFailException("Доступ ограничен!");

        return userData;
    }

    private String getBasicToken(String username, String password) {
        String usernamePasswordPair = username + ":" + password;
        String authHeader = new String(Base64.getEncoder().encode(usernamePasswordPair.getBytes()));
        return "Basic : " + authHeader;
    }

    private void validateVariablesForNullOrIsEmpty(CreateUserModel createUserModel) {
        if (createUserModel.getFullName() == null || createUserModel.getFullName().isEmpty())
            throw new ApiFailException("Введите свое полное имя.");
        if (createUserModel.getEmail() == null || createUserModel.getEmail().isEmpty())
            throw new ApiFailException("Введите свой эл.адрес.");
        if (createUserModel.getUsername() == null || createUserModel.getUsername().isEmpty())
            throw new ApiFailException("Придумайте логин.");
        if (createUserModel.getPassword() == null || createUserModel.getPassword().isEmpty())
            throw new ApiFailException("Придумайте пароль.");
    }

    private void validateVariablesForNullOrIsEmptyForUpdate(UpdateUserModel updateUserModel) {
        if (updateUserModel.getFullName() == null)
            throw new ApiFailException("Введите свое полное имя.");
        if (updateUserModel.getEmail() == null)
            throw new ApiFailException("Введите свой эл.адрес.");
        if (updateUserModel.getUsername() == null)
            throw new ApiFailException("Придумайте логин.");
        if (updateUserModel.getPassword() == null)
            throw new ApiFailException("Придумайте пароль.");
    }

    private void checkUsernameAndEmail(BaseUserModel baseUserModel) {
        User userGetByUsername = getByUsername(baseUserModel.getUsername());
        User userGetByEmail = getByEmail(baseUserModel.getEmail());

        if (userGetByUsername != null)
            throw new ApiFailException("Username " + userGetByUsername.getUsername() + " уже знаято");

        if (userGetByEmail != null)
            throw new ApiFailException("Email " + userGetByEmail.getEmail() + " уже занято");
    }

    private void checkUserActiveStatus(User user) {
        if (user.getIsActive() == -1) throw new ApiFailException("Такого пользователя не сеществует");
        if (user.getIsActive() == 0) {
//            UserAuth userAuth =
        }
    }
}
