package com.example.easytolearn.service;

import com.example.easytolearn.entity.UserBalance;
import com.example.easytolearn.model.balance.UpdateUserBalanceModel;
import com.example.easytolearn.model.balance.UserBalanceModel;

import java.util.List;

public interface UserBalanceService extends BaseService<UserBalance> {
    UserBalanceModel toUpBalance(UpdateUserBalanceModel updateUserBalanceModel);

    UserBalance getUserBalanceByUserId(Long userId);

    UserBalanceModel getUserBalanceModelByUserId(Long userId);

    UserBalanceModel getUserBalanceModelById(Long id);
}