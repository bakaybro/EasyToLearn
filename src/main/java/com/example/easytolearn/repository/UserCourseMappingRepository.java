package com.example.easytolearn.repository;

import com.example.easytolearn.entity.UserCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCourseMappingRepository extends JpaRepository<UserCourseMapping, Long> {
    List<UserCourseMapping> findAllByUser_Id(Long userId);
    List<UserCourseMapping> findAllByCourse_Id(Long courseId);
    Optional<UserCourseMapping> findByCourse_IdAndUser_Id(Long courseId, Long userId);
}
