package com.example.easytolearn.repository;

import com.example.easytolearn.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByCourseIdOrderByIdAsc(Long courseId);
}
