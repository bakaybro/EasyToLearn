package com.example.easytolearn.repository;

import com.example.easytolearn.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByUserId(Long userId);

    List<Course> findAllByCategoryId(Long categoryId);
}
