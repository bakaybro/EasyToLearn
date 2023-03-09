package com.example.easytolearn.service;

import com.example.easytolearn.entity.UserAuth;
import com.example.easytolearn.model.UserAuthModel;

import java.util.List;

public interface UserAuthService extends BaseService<UserAuth> {
    boolean hasThreeFailsLastsLogsByUserId(Long userId);
    UserAuth getLastLogByUserId(Long userId);
    List<UserAuthModel> getAllByUserId(Long userId);
}
