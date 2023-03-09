package com.example.easytolearn.repository;

import com.example.easytolearn.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByCourseIdOrderByIdAsc(Long courseId);

    @Query(value = "SELECT * FROM lessons l WHERE l.course_id = :courseId ORDER BY id ASC LIMIT 3;", nativeQuery = true)
    List<Lesson> findFirstThreeLessonsByCourseId(@Param("courseId") Long courseId);

    @Query(value = "SELECT COUNT(*) FROM lessons l WHERE l.course_id = :courseId", nativeQuery = true)
    Long getCountLessonByCourseId(@Param("courseId") Long courseId);
}
