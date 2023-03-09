package com.example.easytolearn.service;

import com.example.easytolearn.entity.UserBalance;
import com.example.easytolearn.model.balance.UpdateUserBalanceModel;
import com.example.easytolearn.model.balance.UserBalanceModel;

public interface UserBalanceService extends BaseService<UserBalance> {
    UserBalanceModel toUpBalance(UpdateUserBalanceModel userUserBalanceModel);

    UserBalance getUserBalanceByUserId(Long userId);

    UserBalanceModel getUserBalanceModelByUserId(Long userId);

    UserBalanceModel getUserBalanceModelById(Long id);
}
