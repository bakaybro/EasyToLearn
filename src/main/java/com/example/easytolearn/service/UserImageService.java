package com.example.easytolearn.service;

import com.example.easytolearn.entity.UserImage;
import com.example.easytolearn.model.userImage.UserImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserImageService extends BaseService<UserImage> {
    UserImageModel createUserImage(MultipartFile multipartFile);
    UserImageModel updateUserImage(MultipartFile multipartFile);
    UserImageModel deleteImage();
    UserImageModel getUserImageModelById(Long id);
    UserImageModel getUserImageModelByUserId(Long userId);
    UserImage getUserImageByUserId(Long userId);
    List<UserImageModel> getAllUserImageModel();
}
