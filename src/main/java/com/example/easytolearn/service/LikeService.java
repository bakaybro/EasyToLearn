package com.example.easytolearn.service;

import com.example.easytolearn.entity.Like;
import com.example.easytolearn.model.LikeModel;

import java.util.List;

public interface LikeService extends BaseService<Like> {
    LikeModel createLikeByCourseId(Long courseId);

    LikeModel deleteLike(Long courseId);

    LikeModel getLikeModelById(Long id);

    List<LikeModel> getAllLikeModelByCourseId(Long id);
}