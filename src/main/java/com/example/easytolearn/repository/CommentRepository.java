package com.example.easytolearn.repository;

import com.example.easytolearn.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCourseId(Long courseId);

    List<Comment> findAllByUserId(Long userId);
}
