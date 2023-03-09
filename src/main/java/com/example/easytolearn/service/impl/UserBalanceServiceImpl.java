package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.UserBalanceConverter;
import com.example.easytolearn.entity.UserBalance;
import com.example.easytolearn.model.balance.UpdateUserBalanceModel;
import com.example.easytolearn.model.balance.UserBalanceModel;
import com.example.easytolearn.repository.UserBalanceRepository;
import com.example.easytolearn.service.UserBalanceService;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBalanceServiceImpl implements UserBalanceService {

    private UserService userService;
    private final UserBalanceRepository userBalanceRepository;
    private final UserBalanceConverter userBalanceConverter;

    @Override
    public UserBalance save(UserBalance userBalance) {
        return userBalanceRepository.save(userBalance);
    }

    @Override
    public UserBalance getById(Long id) {
        return null;
    }

    @Override
    public List<UserBalance> getAll() {
        return null;
    }

    @Override
    public UserBalanceModel toUpBalance(UpdateUserBalanceModel userUserBalanceModel) {
        return null;
    }

    @Override
    public UserBalance getUserBalanceByUserId(Long userId) {
        return null;
    }

    @Override
    public UserBalanceModel getUserBalanceModelByUserId(Long userId) {
        return null;
    }

    @Override
    public UserBalanceModel getUserBalanceModelById(Long id) {
        return null;
    }
}
