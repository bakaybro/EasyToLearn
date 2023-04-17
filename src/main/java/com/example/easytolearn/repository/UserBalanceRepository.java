package com.example.easytolearn.repository;

import com.example.easytolearn.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
    Optional<UserBalance> findByUser_Id(Long userId);
}
