package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.UserAuthConverter;
import com.example.easytolearn.entity.UserAuth;
import com.example.easytolearn.model.UserAuthModel;
import com.example.easytolearn.repository.UserAuthRepository;
import com.example.easytolearn.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserAuthRepository userAuthRepository;
    private final UserAuthConverter userAuthConverter;

    @Override
    public UserAuth save(UserAuth userLog) {
        return userAuthRepository.save(userLog);
    }

    @Override
    public UserAuth getById(Long id) {
        return userAuthRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserAuth> getAll() {
        return userAuthRepository.findAll();
    }

    @Override
    public List<UserAuthModel> getAllByUserId(Long id) {
        return userAuthRepository.findAllByUserId(id).stream()
                .map(userAuthConverter::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public boolean hasThreeFailsLastsLogsByUserId(Long id) {
        return userAuthRepository.hasThreeFailsInARowByUserId(id);
    }

    @Override
    public UserAuth getLastLogByUserId(Long id) {
        return userAuthRepository.findLastLogByUserId(id).orElse(null);
    }
}
