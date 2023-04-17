package com.example.easytolearn.service;

import com.example.easytolearn.entity.UserLog;
import com.example.easytolearn.model.UserLogModel;

import java.util.List;

public interface UserLogService extends BaseService<UserLog> {
    boolean hasThreeFailsLastsLogsByUserId(Long id);

    UserLog getLastLogByUserId(Long id);

    List<UserLogModel> getAllByUserId(Long id);
}