package com.example.easytolearn.service.impl;

import com.example.easytolearn.entity.UserRole;
import com.example.easytolearn.repository.UserRoleRepository;
import com.example.easytolearn.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole getById(Long id) {
        return userRoleRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleRepository.findAll();
    }
}
