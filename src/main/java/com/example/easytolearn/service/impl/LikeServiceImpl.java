package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.LikeConverter;
import com.example.easytolearn.entity.Course;
import com.example.easytolearn.entity.Like;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.LikeModel;
import com.example.easytolearn.repository.LikeRepository;
import com.example.easytolearn.service.CourseService;
import com.example.easytolearn.service.LikeService;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    private final LikeRepository likeRepository;
    private final LikeConverter likeConverter;

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public LikeModel createLikeByCourseId(Long courseId) {
        User user = userService.getCurrentUser();
        Course course = courseService.getById(courseId);

        Like like = likeRepository
                .findByCourse_IdAndUser_Id(courseId, user.getId())
                .orElse(null);

        validateLike(course, like);

        like = new Like();
        like.setCourse(course);
        like.setUser(user);
        save(like);
        return likeConverter.convertFromEntity(like);
    }

    @Override
    public Like getById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    @Override
    public LikeModel getLikeModelById(Long id) {
        return likeConverter.convertFromEntity(getById(id));
    }

    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    @Override
    public List<LikeModel> getAllLikeModelByCourseId(Long id) {
        return likeRepository
                .findAllByCourse_Id(id)
                .stream()
                .map(likeConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public LikeModel deleteLike(Long courseId) {
        Long currentUserId = userService.getCurrentUser().getId();
        Like dataLike = likeRepository.findByCourse_IdAndUser_Id(courseId, currentUserId).orElse(null);
        if (dataLike == null)
            throw new ApiFailException("Like not found");

        likeRepository.delete(dataLike);
        return likeConverter.convertFromEntity(dataLike);
    }

    private void validateLike(Course course, Like like) {
        if (course == null)
            throw new ApiFailException("Course not found");

        if (like != null)
            throw new ApiFailException("Like is already exists");
    }
}