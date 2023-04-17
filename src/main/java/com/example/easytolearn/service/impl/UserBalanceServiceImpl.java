package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.UserBalanceConverter;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserBalance;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.balance.UpdateUserBalanceModel;
import com.example.easytolearn.model.balance.UserBalanceModel;
import com.example.easytolearn.repository.UserBalanceRepository;
import com.example.easytolearn.service.UserBalanceService;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBalanceServiceImpl implements UserBalanceService {
    @Autowired
    private UserService userService;
    private final UserBalanceRepository userBalanceRepository;
    private final UserBalanceConverter userBalanceConverter;

    @Override
    public UserBalance save(UserBalance userBalance) {
        return userBalanceRepository.save(userBalance);
    }

    @Override
    public UserBalance getUserBalanceByUserId(Long userId) {
        return userBalanceRepository.findByUser_Id(userId).orElse(null);
    }

    @Override
    public UserBalance getById(Long id) {
        return userBalanceRepository.findById(id).orElse(null);
    }

    @Override
    public UserBalanceModel getUserBalanceModelById(Long id) {
        return userBalanceConverter.convertFromEntity(getById(id));
    }

    @Override
    public UserBalanceModel getUserBalanceModelByUserId(Long userId) {
        UserBalance userBalance = getUserBalanceByUserId(userId);
        return userBalanceConverter.convertFromEntity(userBalance);
    }

    @Override
    public List<UserBalance> getAll() {
        return userBalanceRepository.findAll();
    }

    @Override
    public UserBalanceModel toUpBalance(UpdateUserBalanceModel updateUserBalanceModel) {
        String username = updateUserBalanceModel.getUsername();

        User dataUser = getUserWithValidateUsername(username);
        BigDecimal balance = getBalanceWithValidate(updateUserBalanceModel.getBalance());

        UserBalance dataUserBalance = getUserBalanceByUserId(dataUser.getId());
        dataUserBalance.setBalance(dataUserBalance.getBalance().add(balance));
        userBalanceRepository.save(dataUserBalance);
        return userBalanceConverter.convertFromEntity(dataUserBalance);
    }

    private User getUserWithValidateUsername(String username) {
        if (username == null || username.isEmpty())
            throw new ApiFailException("Username не заполнен");
        User dataUser = userService.getByUsername(username);
        if (dataUser == null)
            throw new ApiFailException("Пользователь " + username + " не найден");

        return dataUser;
    }

    private BigDecimal getBalanceWithValidate(BigDecimal balance) {
        if (balance == null)
            throw new ApiFailException("Баланс пользователя не заполнен");
        if (balance.compareTo(BigDecimal.ZERO) <= 0)
            throw new ApiFailException("Сумма не должна быть меньше или равна 0");

        return balance;
    }
}