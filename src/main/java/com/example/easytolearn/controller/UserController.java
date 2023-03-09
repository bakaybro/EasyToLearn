package com.example.easytolearn.controller;

import com.example.easytolearn.entity.User;
import com.example.easytolearn.model.user.CreateUserModel;
import com.example.easytolearn.model.user.UserProfileDataModel;
import com.example.easytolearn.service.UserService;
import com.example.easytolearn.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseMessage<UserProfileDataModel> save(@RequestBody CreateUserModel createUserModel) {
        return new ResponseMessage<UserProfileDataModel>().prepareSuccessMessage(userService.createUser(createUserModel));
    }

    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAll();
    }

}
