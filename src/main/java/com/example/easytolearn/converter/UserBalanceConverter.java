package com.example.easytolearn.converter;

import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserBalance;
import com.example.easytolearn.model.balance.UserBalanceModel;
import org.springframework.stereotype.Component;

@Component
public class UserBalanceConverter extends BaseConverter<UserBalanceModel, UserBalance> {

    public UserBalanceConverter() {
        super(UserBalanceConverter::convertToModel, UserBalanceConverter::convertToModel);
    }

    private static UserBalanceModel convertToModel(UserBalance entityToConvert) {
        if (entityToConvert == null) return null;

        return UserBalanceModel.builder()
                .id(entityToConvert.getId())
                .userId(entityToConvert.getUser().getId())
                .userBalance(entityToConvert.getBalance())
                .build();
    }

    private static UserBalance convertToModel(UserBalanceModel modelToConvert) {
        if (modelToConvert == null) return null;

        UserBalance userBalance = new UserBalance();
        userBalance.setId(modelToConvert.getId());
        userBalance.setBalance(modelToConvert.getUserBalance());

        if (modelToConvert.getUserId() != null) {
            User user = new User();
            user.setId(modelToConvert.getUserId());
            userBalance.setUser(user);
        }
        return userBalance;
    }
}