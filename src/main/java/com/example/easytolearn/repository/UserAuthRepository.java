package com.example.easytolearn.repository;

import com.example.easytolearn.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    List<UserAuth> findAllByUserId(Long id);
}
