package com.example.easytolearn.repository;

import com.example.easytolearn.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByCourseId(Long courseId);

    Optional<Like> findByCourseIdAndUserId(Long courseId, Long userId);
}
