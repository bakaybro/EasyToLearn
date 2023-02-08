package com.example.easytolearn.repository;

import com.example.easytolearn.entity.UserCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCourseMappingRepository extends JpaRepository<UserCourseMapping, Long> {
    List<UserCourseMapping> findAllByUserId(Long userId);
    List<UserCourseMapping> findAllByCourseId(Long courseId);
    Optional<UserCourseMapping> findByCourseIdAndUserId(Long courseId, Long userId);
}
