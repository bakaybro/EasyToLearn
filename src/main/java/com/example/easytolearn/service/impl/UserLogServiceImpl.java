package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.UserLogConverter;
import com.example.easytolearn.entity.UserLog;
import com.example.easytolearn.model.UserLogModel;
import com.example.easytolearn.repository.UserLogRepository;
import com.example.easytolearn.service.UserLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLogServiceImpl implements UserLogService {
    private final UserLogRepository userLogRepository;
    private final UserLogConverter userLogConverter;

    @Override
    public UserLog save(UserLog userLog) {
        return userLogRepository.save(userLog);
    }

    @Override
    public UserLog getById(Long id) {
        return userLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserLog> getAll() {
        return userLogRepository.findAll();
    }

    @Override
    public List<UserLogModel> getAllByUserId(Long id) {
        return userLogRepository.findAllByUser_Id(id).stream()
                .map(userLogConverter::convertFromEntity).collect(Collectors.toList());
    }

    @Override
    public boolean hasThreeFailsLastsLogsByUserId(Long id) {
        return userLogRepository.hasThreeFailsInARowByUserId(id);
    }

    @Override
    public UserLog getLastLogByUserId(Long id) {
        return userLogRepository.findLastLogByUserId(id).orElse(null);
    }
}